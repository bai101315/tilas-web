package com.bwq.tilaswebmanage.filter;

import com.alibaba.fastjson.JSONObject;
import com.bwq.tilaswebmanage.pojo.Result;
import com.bwq.tilaswebmanage.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
         HttpServletRequest req = (HttpServletRequest)servletRequest;
         HttpServletResponse resp = (HttpServletResponse)servletResponse;
        //获取请求url
        String  url = req.getRequestURL().toString();
        log.info("请求的url:{}",url);
        // 是否为login，是：放行，else：进行登陆检验
        if(url.contains("login")){
            log.info("登录操作，直接放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // 保存token
        String jwt = req.getHeader("token");
        // 判断令牌是否存在
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 需要手动换位json格式
            String nologin = JSONObject.toJSONString(error);
            resp.getWriter().write(nologin);
            return;
        }
        //判断令牌是否正确

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {// jwt解析失败
            e.printStackTrace();
            // 解析令牌失败，返回未登录的错误信息
            log.info("请求头为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 需要手动换位json格式
            String nologin = JSONObject.toJSONString(error);
            resp.getWriter().write(nologin);
            return;
        }

        //放行
        log.info("成功，放行");
        filterChain.doFilter(req,resp);



    }
}
