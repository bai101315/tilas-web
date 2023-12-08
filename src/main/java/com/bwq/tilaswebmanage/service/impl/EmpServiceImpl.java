package com.bwq.tilaswebmanage.service.impl;

import com.bwq.tilaswebmanage.mapper.EmpMapper;
import com.bwq.tilaswebmanage.pojo.Emp;
import com.bwq.tilaswebmanage.pojo.PageBean;
import com.bwq.tilaswebmanage.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate start, LocalDate end) {
        //1,设置分页参数
        PageHelper.startPage(page,pageSize);
        //2，执行正常查询操作
        List<Emp> empList = empMapper.list(name, gender, start, end);
        Page<Emp> p = (Page<Emp>) empList;
        //3，封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        //需要先补充信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassWord(emp);
    }


}
