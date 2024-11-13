package com.zts.demo.biz;
import com.zts.demo.entity.Renter;
import com.zts.demo.mapper.RenterMapper;
import com.zts.demo.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RenterBiz {
    @Autowired
    private RenterMapper mapper;

    public RenterMapper getMapper() {
        return mapper;
    }

    public void setMapper(RenterMapper mapper) {
        this.mapper = mapper;
    }

    public Renter selectByKey(int key){
        Renter renter=mapper.selectByKey(key);
        if(renter!=null){
            return renter;
        }else{
            throw new MyException("查不到该账号对应的用户");
        }
    }

    public boolean checkSignup(String ent,String phone){
        System.out.println("checkSignup开始调用");
        try {
            Renter target= mapper.check(ent,phone);
            if(target!=null){
                System.out.println("该企业已注册过!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("checkSignup失败: " + e.getMessage());
            e.printStackTrace(); // 打印异常的堆栈跟踪
        }
        return true;
    }


    public int addRenter(Renter renter){
        System.out.println("addRenter开始调用");
        try {
            int flag = mapper.insertRenter(renter);
            if(flag > 0){
                System.out.println("插入成功!");
                return flag;
            }
        } catch (Exception e) {
            System.out.println("插入失败: " + e.getMessage());
            e.printStackTrace(); // 打印异常的堆栈跟踪
        }
        return 0;
    }
    public List<Renter> getRenterList(){
        return mapper.selectRenters();
    }

}

