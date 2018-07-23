package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContentCategory;

public interface TbContentCategoryMapper {
	/**
	 * 根据分类id查询商品分类
	 * @param parentId
	 * @return 
	 */
	public List<TbContentCategory> findTbContentCategoryById(long parentId);
	/**
	 * 添加一个内容分类到数据库
	 * @param tbContentCategory 需要添加的内容分类对象
	 */
	public void insertTbContentCategory(TbContentCategory tbContentCategory);
	/**
	 * 根据父类id 查询当前分类的父类
	 * @param id 查询条件，传入的参数是父类id
	 * @return 
	 */
	public TbContentCategory getTbContentCategoryById(long id);
	/**
	 * 修改内容分类
	 * @param contentCategory
	 */
	public void updateTbContentCategory(TbContentCategory contentCategory);
	
	

}