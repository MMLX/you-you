package com.taotao.mapper;


import com.taotao.pojo.TbItemParamItem;

public interface TbItemParamItemMapper {
    /**
     * 吧规格参数保存到数据库中
     * @param tbItemParamItem 需要保存的规格参数
     */
    void insertTbitemParamItem(TbItemParamItem tbItemParamItem);

    /**
     * 根据商品id 查询商品的规格参数
     * @param itemId
     * @return
     */
    TbItemParamItem getItemParamItemByItemId(long itemId);
}