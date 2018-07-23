package com.taotao.mapper;

import com.taotao.pojo.TbItemDesc;

public interface TbItemDescMapper {
	/**
	 * 添加商品描述
	 * @param tbitemdesc 需要添加的商品描述对象
	 */
	public void insertTbitemdesc(TbItemDesc tbitemdesc);

	public TbItemDesc getItemDescById(long itemId);
}