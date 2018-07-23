package com.taotao.mapper;


import com.taotao.pojo.TbOrder;

public interface TbOrderMapper {
    /**
     * 插入订单数据到订单表
     * @param tbOrder 需要插入的订单数据
     */
    void insertOrder(TbOrder tbOrder);
}