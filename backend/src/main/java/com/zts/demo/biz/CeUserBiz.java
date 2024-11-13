package com.zts.demo.biz;
import com.zts.demo.entity.CeUser;
import com.zts.demo.mapper.CeUserMapper;
import com.zts.demo.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CeUserBiz {
    @Autowired
    private CeUserMapper mapper;

    public CeUserMapper getMapper() {
        return mapper;
    }

    public void setMapper(CeUserMapper mapper) {
        this.mapper = mapper;
    }

    public CeUser selectById(String id){
        CeUser ceuser=mapper.selectById(id);
        if(ceuser!=null){
            return ceuser;
        }else{
            throw new MyException("查不到对应的用户");
        }
    }

    public CeUser checkEntLogin(String ent, String password){
        return mapper.checkEntLogin(ent,password);
    }

    public CeUser checkAdmLogin(String usm, String password){
        return mapper.checkAdmLogin(usm,password);
    }

    public int addCeUser(CeUser ceuser){
        System.out.println("addCeUser开始调用");
        try {
            int flag = mapper.insertCeUser(ceuser);
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

    public boolean updateCeUserPassword(String pwd,String id){
        try {
            System.out.println("Biz开始执行");
            int res = mapper.updateCeUserPassword(pwd,id);
            System.out.println("Biz处的update执行，结果是:" + res);
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，例如记录日志或抛出运行时异常
            return false;
        }
    }

    public boolean updateCeUserAccount(CeUser ceuser){
        try{
            System.out.println("UserBiz处的update执行开始");
            int res=mapper.updateCeUserAccount(ceuser);
            System.out.println("UserBiz处的update执行结束，结果是:"+res);
            return res>0;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<CeUser> getCeUserList(){
        return mapper.selectCeUsers();
    }

}

