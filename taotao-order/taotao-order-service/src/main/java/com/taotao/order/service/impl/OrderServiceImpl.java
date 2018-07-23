package com.taotao.order.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_ID_BEGIN}")
    private String ORDER_ID_BEGIN;
    @Value("${ORDER_ITEM_ID_GEN_KEY}")
    private String ORDER_ITEM_ID_GEN_KEY;

    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo) {
        /**
         * 因为订单号是我们自己生成的他必须是一串数字而且自增，所以可以定义一个初始值
         * 然后把这个初始值存入redis中通过redis的自增来完成
         */
        if(!jedisClient.exists(ORDER_GEN_KEY)){
            jedisClient.set(ORDER_GEN_KEY,ORDER_ID_BEGIN);
        }
        //redis中有初始值了，自增
        String orderId = jedisClient.incr(ORDER_GEN_KEY).toString();
        orderInfo.setOrderId(orderId);
        //包邮
        orderInfo.setPostFee("0");
        //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        Date date = new Date();
        orderInfo.setCreateTime(date);
        orderInfo.setUpdateTime(date);
        //订单表中的数据填充完毕 注意 这里的物流信息是第三方的反馈信息
        tbOrderMapper.insertOrder(orderInfo);

        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        //插入订单中的商品表
        for (TbOrderItem orderItem: orderItems) {
            //生成明细id
            Long orderItemId = jedisClient.incr(ORDER_ITEM_ID_GEN_KEY);
            orderItem.setId(orderItemId.toString());
            //订单号id
            orderItem.setOrderId(orderId);
            tbOrderItemMapper.insertOrderItem(orderItem);
        }

        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        //吧订单号和用户地址表关联
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        //插入用户地址表
        tbOrderShippingMapper.insertOrderShipping(orderShipping);
        return TaotaoResult.ok(orderId);
    }
}
