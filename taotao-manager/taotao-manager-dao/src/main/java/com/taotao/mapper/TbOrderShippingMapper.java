package com.taotao.mapper;


import com.taotao.pojo.TbOrderShipping;

public interface TbOrderShippingMapper {
    /**
     * 往数据库中插入指定订单中的用户地址信息
     * @param tbOrderShipping 某一个订单的用户地址信息
     */
    void insertOrderShipping(TbOrderShipping tbOrderShipping);
}