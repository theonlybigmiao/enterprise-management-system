package com.zts.demo.mapper;
import com.zts.demo.entity.Renter;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RenterMapper {
    @Select("select * from renter")
    List<Renter> selectRenters();
    @Select("select * from renter where key=#{key}")
    Renter selectByKey(int key);
    @Select("select * from renter where ent=#{ent} and phone=#{phone}")
    Renter check(@Param("ent") String ent,@Param("phone") String phone);
    @Insert("insert into renter values(#{ent},#{key},#{phone},#{pwd})")
    int insertRenter(Renter renter);
//    @Delete("delete from user where usernumber=#{usernumber}")
//    int deleteByUserNumber(String usernumber);
//    @Update("update user set password=#{password},name=#{name} where usernumber=#{usernumber}")
//    int updateByUserNumber(@Param("password") String password, @Param("name") String name, @Param("usernumber") String usernumber);
//
//    @Update("update user set account=#{account} where usernumber=#{usernumber}")
//    int updateUserAccount(@Param("account") double account,@Param("usernumber") String usernumber);
}
