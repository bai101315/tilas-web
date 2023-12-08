package com.bwq.tilaswebmanage.aop;


import com.alibaba.fastjson.JSONObject;
import com.bwq.tilaswebmanage.mapper.OperateLogMapper;
import com.bwq.tilaswebmanage.pojo.OperateLog;
import com.bwq.tilaswebmanage.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect //需要定义当前类是切面类
@Slf4j
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.bwq.tilaswebmanage.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //当前登录的员工ID，根据请求头的jwt令牌解析
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        //当前操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParam = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime = end - begin;

        //记录日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParam,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP中记录操作日志：{}",operateLog);

        return result;
    }

}
