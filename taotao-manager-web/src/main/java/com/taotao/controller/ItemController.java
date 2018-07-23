package com.taotao.controller;

import com.taotao.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId ){
		TbItem tbitem = itemService.getItemById(itemId);
		return tbitem;
	}
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(int page,int rows){
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		
		return result;
	}
	///item/save
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItem(TbItem tbitem,String desc,String itemParams){
		// 200 成功
		TaotaoResult result = itemService.addItem(tbitem, desc,itemParams);
		return result;
	}
	
	
}
