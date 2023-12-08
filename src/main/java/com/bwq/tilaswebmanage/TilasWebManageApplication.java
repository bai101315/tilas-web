package com.bwq.tilaswebmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan //这样已经开启了对javaWeb的支持
@SpringBootApplication
public class TilasWebManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasWebManageApplication.class, args);
    }

}
