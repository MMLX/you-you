package com.taotao.order.interceptor;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Value("${TT_TOKEN}")
    private String TT_TOKEN;
    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         *  什么是controller？ 类
         *  什么是handler？    类里面的方法
         *  handler执行之前调用
         *  开发思路 先把报错的写到前面
         *  1.从cookie里面查询token
         *  2.查询和查询不到
         *      查询不到：跳转到sso的登录页面(怎么跳转，应该回到刚才的页面)
         *      查询到：走下面的逻辑
         *  3.拿着token调用sso查询redis是否过期
         *      过期：登录
         *      不过期：直接放行
         *
         *  true就放行  false就拦截
         */
        String token = CookieUtils.getCookieValue(request, TT_TOKEN);
        if (StringUtils.isBlank(token)) {
            //得到当前url
            String url = request.getRequestURL().toString();
            //跳转到登录页面 并且把当前的url地址带上
            response.sendRedirect(SSO_LOGIN_URL + "?redirectUrl=" + url);
            return false;
        }
        TaotaoResult result = userService.getUserByToken(token);
        if (result.getStatus() != 200) {
            //得到当前url
            String url = request.getRequestURL().toString();
            //跳转到登录页面 并且把当前的url地址带上
            response.sendRedirect(SSO_LOGIN_URL + "?redirectUrl=" + url);
            return  false;
        }
        //必须把用户给保存起来
        TbUser tbUser = (TbUser) result.getData();
        request.setAttribute("user",tbUser);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /**
         * handler执行之后,modelAndView返回之前
         */
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /**
         * handler执行之后,modelAndView返回之后
         */
    }
}
