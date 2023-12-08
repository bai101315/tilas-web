package com.bwq.tilaswebmanage.mapper;

import com.bwq.tilaswebmanage.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询所有部门
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门信息
     * @param dept
     */
    @Insert("insert into dept (name,create_time,update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 根据id查询某一部门信息
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id}")
    Dept find(Integer id);

    /**
     * 根据部门信息更改相关的内容
     * @param dept
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
