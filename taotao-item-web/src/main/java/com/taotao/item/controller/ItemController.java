package com.taotao.item.controller;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable long itemId, Model model) {
        //商品基本信息
        TbItem tbItem = itemService.getItemById(itemId);
        //返回给页面的对象
        Item item = new Item(tbItem);
        //返回给页面的信息
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping(value = "/item/desc/{itemId}")
    @ResponseBody
    public String showItemDesc(@PathVariable long itemId,Model model){
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        return itemDesc.getItemDesc();
    }

    @RequestMapping(value = "/item/param/{itemId}")
    @ResponseBody
    public String showItemParam(@PathVariable long itemId,Model model){
        String itemParam = itemService.getItemParamItemByItemId(itemId);

        return itemParam;
    }


}
