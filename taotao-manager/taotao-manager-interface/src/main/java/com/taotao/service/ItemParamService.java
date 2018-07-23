package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
    /**
     * 根据分类id查询指定分类下面是否有规格参数模板存在
     * @param itemCatIid 分类id
     * @return  200表示该分类有规格参数模板，否则返回ok
     */
    TaotaoResult getItemParamByCid(long itemCatIid);

    /**
     * 保存模板json数据到数据库中的模板表中
     * @param tbItemParam 需要保存的模板json数据
     * @return 200则表示成功 否则返回ok
     */
    TaotaoResult addItemParam(TbItemParam tbItemParam);
}
