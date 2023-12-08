package com.bwq.tilaswebmanage.service.impl;

import com.bwq.tilaswebmanage.mapper.DeptLogMapper;
import com.bwq.tilaswebmanage.pojo.DeptLog;
import com.bwq.tilaswebmanage.service.DeptLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
