package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Controller
public class SearchController {
    @Value("${ITEM_ROWS}")
    private int ITEM_ROWS;
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString,
                         @RequestParam(defaultValue = "1") Integer page, Model model) {
        //商品集合 总记录条数 总页数
        try {
            byte[] bytes = queryString.getBytes("iso-8859-1");
            queryString = new String(bytes, "utf-8");
            SearchResult result = searchService.search(queryString, page, ITEM_ROWS);
            model.addAttribute("query", queryString);
            model.addAttribute("totalPages", result.getPageCount());
            List<SearchItem> itemList = result.getItemList();
            model.addAttribute("itemList", itemList);
            model.addAttribute("page", page);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "search";
    }

}
