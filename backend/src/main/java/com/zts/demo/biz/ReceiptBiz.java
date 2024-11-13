package com.zts.demo.biz;
import com.zts.demo.entity.Receipt;
import com.zts.demo.mapper.ReceiptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptBiz {
    @Autowired
    private ReceiptMapper mapper;

    public ReceiptMapper getMapper() {
        return mapper;
    }

    public void setMapper(ReceiptMapper mapper) {
        this.mapper = mapper;
    }


    public int addReceipt(Receipt receipt){
        int flag=mapper.insertReceipt(receipt);
        if(flag>0){
            System.out.println("插入成功!");
            return flag;
        }
        return 0;
    }

}
