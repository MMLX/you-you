package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	/**
	 * 查询内容分类节点
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> getContentCategoryList(long parentId);
	/**
	 * 添加一个节点内容到数据库
	 * @param parentId 需要添加的节点的父类id
	 * @param name 需要添加的节点的名称
	 * @return
	 */
	public TaotaoResult addContentCategory(long parentId, String name);
	
	
	

}
