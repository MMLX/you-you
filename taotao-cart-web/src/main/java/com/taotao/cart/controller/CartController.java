package com.taotao.cart.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ItemService itemService;

    @Value("${TT_CART}")
    private String TT_CART;
    @Value("${CART_EXPIRE}")
    private Integer CART_EXPIRE;

    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        List<TbItem> list = getCartList(request);
        boolean flag = false;
        //如果为空集合
        for (TbItem tbItem : list) {
            //他们是对象类型的整型  吧对象类型的整型变成 基本数据类型
            if (tbItem.getId() == itemId.longValue()) {
                //注意 在数据库中num代表的是库存，这里我们用这个属性来保存数量也是行的
                tbItem.setNum(tbItem.getNum() + num);
                flag = true;
                break;
            }
        }
        //代表没有在cookie里面找到相同的商品
        if (!flag) {
            TbItem item = itemService.getItemById(itemId);
            //吧页面想要购买的数量添加进去
            item.setNum(num);
            //在添加商品的时候 图片是有多张的 他们之间是以,号分割的
            String images = item.getImage();
            if (StringUtils.isNotBlank(images)) {
                String image = images.split(",")[0];
                item.setImage(image);
            }
            list.add(item);
        }
        //集合里面一定有值了 并且把他存入我们的cookie里面
        CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(list), CART_EXPIRE, true);
        //跳转到添加成功页面
        return "cartSuccess";
    }

    @RequestMapping("/cart")
    public String showCartList(HttpServletRequest request) {
        List<TbItem> cartList = getCartList(request);
        request.setAttribute("cartList", cartList);
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public TaotaoResult updateNum(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request, HttpServletResponse response) {
        //这里是从cookie里面取出来的
        List<TbItem> list = getCartList(request);
        for (TbItem tbItem : list) {
            if (tbItem.getId() == itemId.longValue()) {
                tbItem.setNum(num);
                break;
            }
        }
        //集合里面一定有值了 并且把他存入我们的cookie里面
        CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(list), CART_EXPIRE, true);
        return TaotaoResult.ok();
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<TbItem> list = getCartList(request);
        for (int i = 0;i<list.size();i++) {
            TbItem tbItem = list.get(i);
            if(tbItem.getId()==itemId.longValue()){
                list.remove(tbItem);
                break;
            }
        }
        //集合里面一定有值了 并且把他存入我们的cookie里面
        CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(list), CART_EXPIRE, true);
        return "cart";
    }


    //用于查询cookie里面是否有相同的商品
    private List<TbItem> getCartList(HttpServletRequest request) {
        //默认使用utf-8编码
        String json = CookieUtils.getCookieValue(request, TT_CART, true);
        if (StringUtils.isBlank(json)) {
            //从cookie里面取数据 取不到直接返回空集合
            return new ArrayList<TbItem>();
        }
        //取到返回指定的数据
        List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
        return list;
    }
}
