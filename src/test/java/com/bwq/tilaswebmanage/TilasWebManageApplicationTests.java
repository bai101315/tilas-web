package com.bwq.tilaswebmanage;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TilasWebManageApplicationTests {

    @Test
    public void testUuid(){
        for(int i = 0; i < 1000; i++){
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    /**
     * JWT令牌测试
     */
    @Test
    public void testGenJwt(){

        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")//签名算法
                .setClaims(claims)// 自定义内容（有效载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置当前令牌的有效期为1h
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析JWT
     */
    @Test
    public void parseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcwMTg0OTcwOX0.dBmuUhK5ydR53tU_XN1VtF3m_lehiDzEWpNbCP4YVXQ")
                .getBody();

        System.out.println(claims);

    }




}
