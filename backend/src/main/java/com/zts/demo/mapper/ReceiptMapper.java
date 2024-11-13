package com.zts.demo.mapper;
import com.zts.demo.entity.Receipt;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ReceiptMapper {
    @Insert("insert into receipt values(#{meetingname},#{name},#{unit},#{email},#{phone},#{gender},#{roomType},#{arrivalMethod},#{arrivalFlight},#{arrivalDate},#{returnMethod},#{returnFlight},#{returnDate},#{notes})")
    int insertReceipt(Receipt receipt);
}
