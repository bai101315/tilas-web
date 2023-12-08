package com.bwq.tilaswebmanage.service;

import com.bwq.tilaswebmanage.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    /**
     *  查询全部部门数据
     * @return 存储Dept对象的集合
     */
    List<Dept> list();

    /**
     *
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    Dept find(Integer id);

    /**
     * 根据部门信息进行更改
     * @param dept
     */
    void update(Dept dept);
}
