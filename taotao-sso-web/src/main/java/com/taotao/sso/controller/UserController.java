package com.taotao.sso.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${COOKIE_TOKEN_KEY}")
    private String COOKIE_TOKEN_KEY;


    @RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult checkData(@PathVariable String param, @PathVariable Integer type) {
        TaotaoResult result = userService.checkData(param, type);
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser tbUser) {
        TaotaoResult result = userService.createUser(tbUser);
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult result = userService.loginUser(userName, passWord);
        if (result.getStatus() == 200) {
            //存入cookie 要把token存入浏览器
            CookieUtils.setCookie(request, response, COOKIE_TOKEN_KEY, result.getData().toString());
        }
        return result;
    }

    @RequestMapping(value = "/token/{token}", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getUserByToken(@PathVariable String token, String callback) {
        TaotaoResult result = userService.getUserByToken(token);
        if (StringUtils.isNotBlank(callback)) {
            String resultStr = callback + "(" + JsonUtils.objectToJson(result) + ");";
            System.out.println(resultStr);
            return resultStr;
        }
        return JsonUtils.objectToJson(result);
    }
}
