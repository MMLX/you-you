package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam.Mode;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.portal.pojo.ItemCat;
import com.taotao.portal.pojo.ItemCatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.Ad1Node;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    //对于用户来说 他的请求地址是 http://localhost:8082/===>  http://localhost:8082/index.jsp
    //web.xml里面的拦截规则 *.html
    @Value("${AD1_CID}")
    private Long AD1_CID;
    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;
    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;
    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;
    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;

    @Autowired
    private ContentService contentService;


    @RequestMapping("/index")
    public String showIndex(Model model) {
        //首页需要什么东西 是首页必须要告诉我们的
        List<TbContent> contentList = contentService.getContentList(AD1_CID);
        List<Ad1Node> reuslt = new ArrayList<Ad1Node>();
        for (TbContent content : contentList) {
            Ad1Node node = new Ad1Node();
            //大图
            node.setSrcB(content.getPic2());
            //设置小图高度
            node.setHeight(AD1_HEIGHT);
            //设置提示 也就是图片的描述
            node.setAlt(content.getTitleDesc());
            node.setWidth(AD1_WIDTH);
            //小图
            node.setSrc(content.getPic());
            //设置大宽度
            node.setWidthB(AD1_WIDTH_B);
            //设置图片跳转的链接
            node.setHref(content.getUrl());
            //设置大高度
            node.setHeightB(AD1_HEIGHT_B);
            reuslt.add(node);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(reuslt));


        return "index";
    }

    // http://localhost:8082/item/cat/itemcat/all.html
    @RequestMapping("/item/cat/itemcat/all")
    @ResponseBody
    public ItemCatResult queryAll(String callback) {
        ItemCatResult result = new ItemCatResult();
        result.setData(getItemCatList(0));
        return result;
    }

    /**
     * 查询数据库得到想要的分类的
     *
     * @return
     */
    private List<?> getItemCatList(long parentId) {
        List<TbItemCat> list = contentService.getItemCatAll(parentId);
        List data = new ArrayList();
        int count = 0;
        for (TbItemCat item : list) {
            ItemCat itemCat = new ItemCat();
            //判断是否是父节点
            if (item.getIsParent()) {

                itemCat.setUrl("/products/" + item.getId() + ".html");
                if (parentId == 0) {
                    //注意 只有第一级目录才是这样 第二级是字符串
                    itemCat.setName("<a href='products/" + item.getId() + ".html'>" + item.getName() + "</a>");
                } else {
                    //第二级目录
                    itemCat.setName(item.getName());
                }
                count ++;
                //递归
                itemCat.setItem(getItemCatList(item.getId()));
                //查询数据库以后得到的一二级目录存放到一个list集合里面去
                data.add(itemCat);


                if (parentId == 0 && count >= 14) {
                    break;
                }


            } else {
                //最底层的子目录 存放的最下级目录
                data.add("/products/" + item.getId() + ".html|" + item.getName());

            }
        }
        return data;
    }
}
