package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface TbContentMapper {
	/**
	 * 根据分类id查询分类内容
	 * @param categoryId 分类id
	 * @return 当前这个分类下面的所有信息
	 */
	public List<TbContent> findTbContentAll(long categoryId);
	/**
	 * 添加一个内容信息到数据库
	 * @param tbContent 需要添加的内容信息
	 */
	public void insertContent(TbContent tbContent);
	
}