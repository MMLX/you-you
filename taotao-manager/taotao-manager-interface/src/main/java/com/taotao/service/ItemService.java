package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

public interface ItemService {
	/**
	 * 根据商品id查询指定商品信息
	 * @param itemId 商品id
	 * @return 指定商品id的信息
	 */
	TbItem getItemById(long itemId);
	/**
	 * 分页显示商品信息
	 * @param page 当前页
	 * @param rows 每页显示条数 默认30条
	 * @return 返回当前页面的记录条数，注意必须是json类型
	 */
	EasyUIDataGridResult getItemList(int page, int rows);
	/**
	 * 添加商品信息，注意有些数据页面没有传递过来需要手动指定 比如商品id
	 * @param tbitem 需要添加的商品对象信息
	 * @param desc 商品描述信息
	 * @param itemParams 商品规格参数
	 * @return 一个自己定义的结果集  里面包含了 {状态码,消息,数据}
	 */
	TaotaoResult addItem(TbItem tbitem, String desc,String itemParams);

	/**
	 * 根据商品id查询商品描述信息
	 * @param itemId 商品id
	 * @return 指定商品的描述信息
	 */
	TbItemDesc getItemDescById(long itemId);

	/**
	 * 根据商品id查询商品规格参数并且把规格参数转化成为html页面 通过model添加到域里面 在jsp页面去展示
	 * @param itemId 商品id
	 * @return html页面里面填充了规格参数
	 */
	String getItemParamItemByItemId(long itemId);
}
