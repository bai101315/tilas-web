package com.bwq.tilaswebmanage.controller;

import com.bwq.tilaswebmanage.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {


    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传：{},{},{}",username,age,image);

        //存储前端传过来的信息，1，存储到本地磁盘，2，存储到云服务器上
        String originalFilename = image.getOriginalFilename();

        //构建唯一的文件名，唯一的识别码uuid
        int index = originalFilename.lastIndexOf('.');
        String exname = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + exname;
        log.info("新的文件名：{}",newFilename);

        image.transferTo(new File("C:\\Users\\BAI\\Desktop\\web"+newFilename));


        return Result.success();
    }
}
