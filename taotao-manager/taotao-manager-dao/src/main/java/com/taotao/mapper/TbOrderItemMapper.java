package com.taotao.mapper;

import com.taotao.pojo.TbOrderItem;

    public interface TbOrderItemMapper {
    /**
     * 往数据库里面插入订单对应的商品
     * @param tbOrderItem 某一个订单对应的商品
     */
    void insertOrderItem(TbOrderItem tbOrderItem);
}