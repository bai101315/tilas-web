package com.bwq.tilaswebmanage.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bwq.tilaswebmanage.pojo.Result;
import com.bwq.tilaswebmanage.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//定义拦截器
@Component //当前拦截器对象
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标资源运行前放行，返回值为true：放行，false：拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求url
        String  url = request.getRequestURL().toString();
        log.info("请求的url:{}",url);
        // 是否为login，是：放行，else：进行登陆检验
        if(url.contains("login")){
            log.info("登录操作，直接放行");
            return true;
        }
        // 保存token
        String jwt = request.getHeader("token");
        // 判断令牌是否存在
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 需要手动换位json格式
            String nologin = JSONObject.toJSONString(error);
            response.getWriter().write(nologin);
            return false;
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
            response.getWriter().write(nologin);
            return false;
        }

        //放行
        log.info("成功，放行");
        return true;
    }

    @Override // 目标资源运行后放行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override // 视图渲染完成后才会执行，最后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
