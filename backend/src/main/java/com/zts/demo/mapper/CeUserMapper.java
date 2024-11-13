package com.zts.demo.mapper;
import com.zts.demo.entity.CeUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CeUserMapper {
    @Select("select * from ceuser")
    List<CeUser> selectCeUsers();
    @Select("select * from ceuser where id=#{id}")
    CeUser selectById(String id);
    @Select("select * from ceuser where pwd=#{pwd}")
    CeUser selectByPassword(String pwd);
    @Select("select * from ceuser where ent=#{ent} and pwd=#{pwd}")
    CeUser checkEntLogin(@Param("ent") String ent,@Param("pwd") String pwd);
//    @Param的情况下String后面那部分也要和字段名相等

    @Select("select * from ceuser where usm=#{usm} and pwd=#{pwd}")
    CeUser checkAdmLogin(@Param("usm") String usm,@Param("pwd") String pwd);
    @Insert("insert into ceuser values(#{usm},#{pwd},#{phone},#{gender},#{email},#{date},#{role},#{ent},#{dept},#{id})")
    int insertCeUser(CeUser ceuser);
//    @Delete("delete from user where usernumber=#{usernumber}")
//    int deleteByUserNumber(String usernumber);
//    @Update("update user set password=#{password},name=#{name} where usernumber=#{usernumber}")
//    int updateByUserNumber(@Param("password") String password, @Param("name") String name, @Param("usernumber") String usernumber);
//
    @Update("update ceuser set usm=#{usm},phone=#{phone},email=#{email},gender=#{gender},dept=#{dept} where id=#{id}")
    int updateCeUserAccount(CeUser ceuser);
    @Update("update ceuser set pwd=#{pwd} where id=#{id}")
    int updateCeUserPassword(@Param("pwd") String pwd,@Param("id") String id);
//    @Update("update user set account=#{account} where usernumber=#{usernumber}")
//    int updateUserAccount(@Param("account") double account,@Param("usernumber") String usernumber);
}
