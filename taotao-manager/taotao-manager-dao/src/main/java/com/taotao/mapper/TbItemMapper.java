package com.taotao.mapper;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;

public interface TbItemMapper {
	/**
	 * 根据商品id查询商品信息
	 * @param itemId 商品id
	 * @return 指定商品id的商品信息
	 */
	public TbItem getItemById(long itemId);
	/**
	 * 分页查询商品信息
	 * @return 返回当前页的记录条数
	 */
	public List<TbItem> getItemList();
	/**
	 * 添加一个商品对象到数据库
	 * @param tbitem
	 */
	public void insertTbItem(TbItem tbitem);
	
	
	
}