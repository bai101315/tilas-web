package com.bwq.tilaswebmanage.controller;

import com.bwq.tilaswebmanage.anno.Log;
import com.bwq.tilaswebmanage.pojo.Dept;
import com.bwq.tilaswebmanage.pojo.Result;
import com.bwq.tilaswebmanage.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController将其送给IOC容器
@RestController
//公共路径，实际路径应该为公共路径+方法上的路径
@RequestMapping("/depts")
@Slf4j
public class DeptController {

//    调用Service进行注入
    @Autowired
    private DeptService deptService;

    /*查询部门数据
    * */
    @GetMapping
    public Result list(){
        log.info("查询所有部门数据");

        List<Dept>deptList = deptService.list();

        return Result.success(deptList);
    }
    /*
     *删除部门
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门:{}",id);
        deptService.delete(id);

        return Result.success();
    }

    /*
    * 新增部门
    * @RequestBody
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
//        调用service新增部门
        deptService.add(dept);

        return Result.success();
    }

    /**
     * 根据id查询部门信息
     * @param id
     * @return dept
     */

    @GetMapping("/{id}")
    public Result find(@PathVariable Integer id){
        log.info("根据id查询部门:{}",id);
        Dept dept =  deptService.find(id);
        return Result.success(dept);
    }

    /**
     * 根据查询到的dept实体类进行数据更改，使用dept数据类来接受json格式数据
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更改部门：{}",dept);
        deptService.update(dept);

        return Result.success();
    }
}
