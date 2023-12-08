package com.bwq.tilaswebmanage.controller;


import com.bwq.tilaswebmanage.anno.Log;
import com.bwq.tilaswebmanage.pojo.Emp;
import com.bwq.tilaswebmanage.pojo.PageBean;
import com.bwq.tilaswebmanage.pojo.Result;
import com.bwq.tilaswebmanage.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){

        log.info("分页查询，参数：{},{},{},{},{},}{}",page,pageSize,name,gender,start,end);
        PageBean pageBean=empService.page(page,pageSize,name,gender,start,end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer>ids){
        log.info("执行批量删除操作，ids:{}",ids);
        empService.delete(ids);

        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("增加员工:{}", emp);
        empService.save(emp);
        return  Result.success();

    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
         log.info("根据ID查询员工信息，id:{}",id);
         Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新的员工信息为：{}",emp);
        empService.update(emp);
        return Result.success();
    }


}
