package com.bwq.tilaswebmanage.mapper;

import com.bwq.tilaswebmanage.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {


    @Insert("insert into dept_log(create_time, description) VALUES (#{createTime}, #{description})")
    void insert(DeptLog deptLog);
}
