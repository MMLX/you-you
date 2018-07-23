package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

public interface OrderService {
    /**
     * 根据页面传递过来的参数生成订单
     * @param orderInfo 商品订单、商品信息、地址
     * @return 里面应该包含订单号
     */
    TaotaoResult createOrder(OrderInfo orderInfo);
}
