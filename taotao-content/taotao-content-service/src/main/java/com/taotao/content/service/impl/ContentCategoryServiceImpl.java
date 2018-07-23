package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		//根据分类id查询分类
		List<TbContentCategory> tbContentCategorys = tbContentCategoryMapper.findTbContentCategoryById(parentId);
		//创建返回结果集
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		//遍历并且填充返回结果集
		for (TbContentCategory tbContentCategory : tbContentCategorys) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			result.add(node);
		}		
		return result;
	}

	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		TbContentCategory tbContentCategory = new  TbContentCategory();
		//设置父类id
		tbContentCategory.setParentId(parentId);
		//设置节点名称
		tbContentCategory.setName(name);
		//设置状态默认正常
		tbContentCategory.setStatus(1);
		tbContentCategory.setSortOrder(1);
		Date date = new Date();
		tbContentCategory.setCreated(date);
		tbContentCategory.setUpdated(date);
		//但是 isParent没有设置 
		tbContentCategory.setIsParent(false);//添加一个子节点的意思
		//但是添加了子节点 必须判断他是否有父节点
		tbContentCategoryMapper.insertTbContentCategory(tbContentCategory);
		
		
		TbContentCategory parentContent = tbContentCategoryMapper.getTbContentCategoryById(parentId);
		if(!parentContent.getIsParent()){
			//则表示原来的节点是子节点并且要把子节点修改成为父节点
			TbContentCategory category = new TbContentCategory();
			category.setId(parentId);
			category.setIsParent(true);
			tbContentCategoryMapper.updateTbContentCategory(category);
		}
		//返回结果集
		return TaotaoResult.ok(tbContentCategory);
	}

}
