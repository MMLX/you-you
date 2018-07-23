package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {
	/**
	 * 查询数据库并且导入到索引库里面去
	 * @return
	 */
	public TaotaoResult importAllItems();

	public TaotaoResult addDocument(long itemId);
}
