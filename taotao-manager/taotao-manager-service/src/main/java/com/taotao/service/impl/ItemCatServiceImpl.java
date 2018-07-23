package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	

	@Override
	public List<EasyUITreeNode> getCatList(long parentId) {
		//根据商品
		List<TbItemCat> catList = tbItemCatMapper.getCatList(parentId);
		//定义返回结果集
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		
		for (TbItemCat cat : catList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			//1代表有节点 true
			node.setState(cat.getIsParent()?"closed":"open");
			result.add(node);	
		}
		
		return result;
	}

}
