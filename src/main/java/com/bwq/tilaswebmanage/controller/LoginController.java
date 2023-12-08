package com.bwq.tilaswebmanage.controller;

import com.bwq.tilaswebmanage.pojo.Emp;
import com.bwq.tilaswebmanage.pojo.Result;
import com.bwq.tilaswebmanage.service.EmpService;
import com.bwq.tilaswebmanage.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录：{}",emp);
        Emp e = empService.login(emp);

        // 登录成功，生成令牌，下发令牌
        if(e != null){

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);// jwt中包含了当前登录的员工信息
            return Result.success(jwt);
        }
        //登录失败，返回提示信息

        return Result.error("用户名或者密码错误");
    }


}
