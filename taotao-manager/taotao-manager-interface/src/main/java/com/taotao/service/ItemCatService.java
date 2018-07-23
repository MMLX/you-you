package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	/**
	 * 调用数据库查询指定父级下的商品分类信息并且把它装载到EasyUITreeNode对象里面
	 * @param parentId 父级目录id
	 * @return EasyUITreeNode对象里面包含id 、 text 、state
	 */
	public List<EasyUITreeNode> getCatList(long parentId);
}
