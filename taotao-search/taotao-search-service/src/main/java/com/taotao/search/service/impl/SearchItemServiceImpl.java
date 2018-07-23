package com.taotao.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.dao.SearchItemMapper;
import com.taotao.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public TaotaoResult importAllItems() {
        //查询数据库得到所有需要导入的数据
        try {
            List<SearchItem> itemList = searchItemMapper.getItemList();
            for (SearchItem searchItem : itemList) {
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("id", searchItem.getId());
                doc.addField("item_title", searchItem.getTitle());
                doc.addField("item_sell_point", searchItem.getSellPoint());
                doc.addField("item_price", searchItem.getPrice());
                doc.addField("item_image", searchItem.getImage());
                doc.addField("item_category_name", searchItem.getCategoryName());
                doc.addField("item_desc", searchItem.getItemDesc());
                solrServer.add(doc);
            }
            //提交
            solrServer.commit();

            return TaotaoResult.ok();

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TaotaoResult.build(500, "报错了");
    }

    @Override
    public TaotaoResult addDocument(long itemId) {
        // 1、根据商品id查询商品信息。
        SearchItem searchItem = searchItemMapper.getItemById(itemId);
        // 2、创建一SolrInputDocument对象。
        SolrInputDocument document = new SolrInputDocument();
        // 3、使用SolrServer对象写入索引库。
        document.addField("id", searchItem.getId());
        document.addField("item_title", searchItem.getTitle());
        document.addField("item_sell_point", searchItem.getSellPoint());
        document.addField("item_price", searchItem.getPrice());
        document.addField("item_image", searchItem.getImage());
        document.addField("item_category_name", searchItem.getCategoryName());
        document.addField("item_desc", searchItem.getItemDesc());
        // 5、向索引库中添加文档。
        try {
            solrServer.add(document);
            solrServer.commit();

            return TaotaoResult.ok();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
