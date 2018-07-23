package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

public interface SearchService {
	/**
	 * 根据当前页面和需要查询的条件，返回商品列表集合、总记录条数、总页数
	 * @param queryString 查询条件
	 * @param page 当前页
	 * @param rows 每页显示条数
	 * @return SearchResult包含了 商品列表集合、总记录条数、总页数
	 */
	public SearchResult search(String queryString, int page, int rows);
}
