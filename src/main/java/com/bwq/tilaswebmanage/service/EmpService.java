package com.bwq.tilaswebmanage.service;

import com.bwq.tilaswebmanage.pojo.Emp;
import com.bwq.tilaswebmanage.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmpService {

    /**
     * 实现分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate start, LocalDate end);

    /**
     * 执行批量删除操作
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据员工信息进行更新
     * @param emp
     */
    void update(Emp emp);

    /**
     * 根据账号和密码查询员工信息
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
