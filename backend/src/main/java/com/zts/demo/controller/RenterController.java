package com.zts.demo.controller;
import com.zts.demo.biz.CeUserBiz;
import com.zts.demo.biz.DepartmentBiz;
import com.zts.demo.biz.RenterBiz;
import com.zts.demo.entity.CeUser;
import com.zts.demo.entity.Department;
import com.zts.demo.entity.Renter;
import com.zts.demo.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
@Controller
@ResponseBody
public class RenterController {
    @Autowired
    private RenterBiz renterBiz;
    @Autowired
    private CeUserBiz ceUserBiz;
    @Autowired
    private DepartmentBiz deptBiz;

    public RenterController(DepartmentBiz deptBiz) {
        this.deptBiz = deptBiz;
    }

    public void setCeUserBiz(CeUserBiz ceUserBiz) {
        this.ceUserBiz = ceUserBiz;
    }

    public void setRenterBiz(RenterBiz biz){
        this.renterBiz=biz;
    }
    @RequestMapping("/renter/list")

    public Map findAll(){
        List<Renter> list=renterBiz.getRenterList();
        for(Renter renter:list){
            System.out.println(renter.toString());
        }
        Map map=new HashMap<>();
        map.put("isOK",true);
        map.put("renters",list);
        return map;
    }


    //    这种添加得在html端就拼好一个user
    @RequestMapping("/renter/signup")
    @ResponseBody
    public Map addUser(@RequestParam("ent") String ent,@RequestParam("phone") String phone,@RequestParam("pwd")String password){
        System.out.println("传回来的ent是:"+ent);
        Renter renter=new Renter(ent,phone,password);
        System.out.println("renter的key是:"+renter.getKey());
        Map res=new HashMap<>();
        if(!renterBiz.checkSignup(ent,phone)){
            res.put("isOK",true);
            res.put("isSignup",true);
            res.put("msg","该企业已注册过！");
            return res;
        }
        int flag1=renterBiz.addRenter(renter);
        if(flag1>0){
            System.out.println("renter插入成功");
        }
        CeUser ceuser=new CeUser(password, MyUtil.getCurrentTime(),"企业管理员",ent);
        Department department=new Department(ent,phone);
        System.out.println("ceuser的id是:"+ceuser.getId());
        int flag2=0;
        try {
            flag2=ceUserBiz.addCeUser(ceuser);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(flag2>0){
            System.out.println("ceuser插入成功");
        }
        int flag3=0;
        try {
            flag3=deptBiz.addDepartment(department);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(flag3>0){
            System.out.println("department插入成功");
        }
        if(flag1>0&&flag2>0&&flag3>0){
            res.put("isOK",true);
            res.put("isSignup",false);
            res.put("msg","注册成功！");
        }
        else{
            res.put("isOK",false);
            res.put("msg","注册失败！");
        }
        return res;
    }


}
