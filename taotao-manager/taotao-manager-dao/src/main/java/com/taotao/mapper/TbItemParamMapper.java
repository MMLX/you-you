package com.taotao.mapper;


import com.taotao.pojo.TbItemParam;

public interface TbItemParamMapper {
    /**
     * 根据分类id查询数据库中规格参数模板表
     * @param itemCatId 分类id
     * @return 当前分类id所对应的模板对象
     */
    TbItemParam getItemParamByCid(long itemCatId);

    /**
     * 吧商品规格参数模板对象存入数据库中
     * @param tbItemParam 商品规格参数模板对象
     */
    void addItemParam(TbItemParam tbItemParam);
}