package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItemCat;

public interface TbItemCatMapper {
	/**
	 * 根据商品分类parentId查询商品分类
	 * @param parentId 父级目录id
	 * @return 这个当前这个父级目录的商品分类信息
	 */
   public List<TbItemCat> getCatList(long parentId);
}