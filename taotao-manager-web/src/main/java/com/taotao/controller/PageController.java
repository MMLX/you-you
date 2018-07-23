package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转的controller
 *
 */
@Controller
public class PageController {
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	//http://localhost:8081/想要跳转的页面的
	@RequestMapping("/{page}")
	public String showPage(String page){
		return page;
	}
	
}
