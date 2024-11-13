package com.zts.demo.mapper;
import com.zts.demo.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface DepartmentMapper {
    @Select("select * from department")
    List<Department> selectAllDepartments();
    @Select("select * from department where name=#{name}")
    Department selectByDepName(String name);
    @Select("SELECT * FROM department WHERE superior =#{superior}")
    List<Department> selectChildren(String superior);
    @Select("select * from department where id=#{id}")
    Department selectByKey(String id);
    @Insert("insert into department values(#{name},#{superior},#{priority},#{head},#{phone},#{email},#{state},#{date},#{id})")
    int insertDep(Department dept);
    @Delete("delete from department where id=#{id}")
    int deleteById(String id);
    @Update("update department set superior=#{superior},name=#{name},priority=#{priority},head=#{head},phone=#{phone},email=#{email},state=#{state} where id=#{id}")
    int updateByKey(Department department);

}
