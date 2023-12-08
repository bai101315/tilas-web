package com.bwq.tilaswebmanage.service.impl;

import com.bwq.tilaswebmanage.mapper.DeptMapper;
import com.bwq.tilaswebmanage.mapper.EmpMapper;
import com.bwq.tilaswebmanage.pojo.Dept;
import com.bwq.tilaswebmanage.pojo.DeptLog;
import com.bwq.tilaswebmanage.service.DeptLogService;
import com.bwq.tilaswebmanage.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    // 默认只有RuntimeException出现才会执行回滚操作，但可以进行参数设置
    @Transactional(rollbackFor = Exception.class) //当前方法已经交给spring进行事务管理，需要全部执行完毕
    public void delete(Integer id) throws Exception {
        try {
            //根据部门ID删除部门
            deptMapper.deleteById(id);
            //部门删除后，里面的员工信息也需要删除
//        if(true){ throw new Exception("出错了");}
//            int i = 1 / 0;
            empMapper.deleteByDeptId(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行该部门的解散操作，这次解散的部门为: "+id+"号部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void add(Dept dept) {
//        先补全sql表中的基本信息
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept find(Integer id) {
        return deptMapper.find(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
