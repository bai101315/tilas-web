package com.bwq.tilaswebmanage.service;


import com.bwq.tilaswebmanage.pojo.DeptLog;
import org.springframework.stereotype.Service;

@Service
public interface DeptLogService {
    /**
     *
     * @param deptLog
     */

    void insert(DeptLog deptLog);
}
