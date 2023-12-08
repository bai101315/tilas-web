package com.bwq.tilaswebmanage.mapper;

import com.bwq.tilaswebmanage.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 员工信息查询
     * @return
     */
//    @Select("select * from emp")
    public List<Emp>list(String name, Short gender, LocalDate start, LocalDate end);

    /**
     * 根据id单个删除或者批量删除id
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 增加员工信息
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "VALUES(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime}) ")
    void save(Emp emp);

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);


    void update(Emp emp);
    @Select("select * from emp where username=#{username} and password=#{password};")
    Emp getByUsernameAndPassWord(Emp emp);

    /**
     * 根据部门ID删除所有员工信息
     * @param deptId
     */
    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);


}
