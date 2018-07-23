package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String queryString, int page, int rows) {
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(queryString);
		//设置默认搜索域
		query.set("df", "item_keywords");
		//开始
		query.setStart(0);
		//每页显示条数
		query.setRows(rows);
		//开启高亮
		query.setHighlight(true);
		//设置那个域高亮
		query.addHighlightField("item_title");
		//前缀
		query.setHighlightSimplePre("<span style='color:red'>");
		//后缀
		query.setHighlightSimplePost("</span>");
		SearchResult result = searchDao.search(query);
		//计算总页数
		long pageCount = result.getRecordCount()%rows==0?result.getRecordCount()/rows:result.getRecordCount()/rows+1;
		result.setPageCount(pageCount);
		return result;
	}

}
