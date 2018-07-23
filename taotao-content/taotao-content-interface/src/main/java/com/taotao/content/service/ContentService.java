package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItemCat;

public interface ContentService {
	/**
	 * 根据内容分类id查询 这个分类内容信息
	 * @param categoryId 分类id
	 * @return total记录条数 ， rows 结果集
	 */
	EasyUIDataGridResult findContentAll(long categoryId);
	/**
	 * 添加一个内容到数据库 注意需要补充两个参数 
	 * @param tbContent 需要添加的内容信息
	 * @return 里面包含了200
	 */
	TaotaoResult addContent(TbContent tbContent);
	/**
	 * 查询数据库得到这个分类指定的内容
	 * @param categoryId 分类id
	 * @return 这个分类下面的所有内容 
	 */
	List<TbContent> getContentList(long categoryId);

	/**
	 * 根据id查询所有分类
	 * @param parentId 分类id
	 * @return 当前id的所有分类
	 */
	List<TbItemCat> getItemCatAll(long parentId);

}
