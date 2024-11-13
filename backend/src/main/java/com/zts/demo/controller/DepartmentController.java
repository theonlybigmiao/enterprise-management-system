package com.zts.demo.controller;
import com.zts.demo.biz.DepartmentBiz;
import com.zts.demo.entity.CeUser;
import com.zts.demo.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
@Controller
@ResponseBody
public class DepartmentController {
    @Autowired
    private DepartmentBiz biz;
    public static CeUser myceuser=new CeUser();
    public List<Department> treeData=new ArrayList<>();

    public DepartmentBiz getBiz(){
        return biz;
    }
    public void setBiz(DepartmentBiz biz){
        this.biz=biz;
    }

    public DepartmentController() {

    }

    @RequestMapping("/department/addDept")
    @ResponseBody
    public Map addDepartment(@RequestParam("superior") String superior,@RequestParam("name") String name,@RequestParam("priority") int priority,@RequestParam("head") String head,@RequestParam("phone") String phone,@RequestParam("email") String email,@RequestParam("status") String status){
        Map res=new HashMap<>();
        String id=biz.selectByDepName(superior).getId();
        Department department=new Department(name,id,priority,head,phone,email,status);
        try{
            if(biz.addDepartment(department)>0){
                res.put("isOK",true);
                res.put("msg","添加成功!");
                System.out.println("添加成功!");
            }
            else{
                res.put("isOK",false);
                res.put("msg","添加失败!");
                System.out.println("添加失败!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }

    @RequestMapping("/department/list")
    @ResponseBody
    public Map findAll(){
        Map map=new HashMap<>();
        try{
            List<Department> list=biz.getDepartmentList();
            map.put("isOK",true);
            map.put("depts",list);
            myceuser=CeUserController.myceuser;
            String superior=biz.selectByDepName(myceuser.getEnt()).getId();
//            将当前用户的部门id传到后端，id为部门主键
            map.put("superior",superior);
//            System.out.println("从myuser获取的权限是:"+myceuser.getRole());
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/department/updateDept")
    @ResponseBody
    public Map updateDepartment(@RequestParam("superior") String superior,@RequestParam("name") String name,@RequestParam("priority") int priority,@RequestParam("head") String head,@RequestParam("phone") String phone,@RequestParam("email") String email,@RequestParam("status") String status,@RequestParam("id") String id){
        Map res=new HashMap<>();
        Department department=new Department(name,superior,priority,head,phone,email,status);
        department.setId(id);
        System.out.println("要修改的对象是:"+department.getId());
        try{
            if(biz.updateDepartment(department)){
                System.out.println("修改成功!");
                res.put("isOK",true);
                res.put("msg","修改成功!");
            }else{
                System.out.println("修改失败!");
                res.put("isOK",false);
                res.put("msg","修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("/department/deleteDept")
    @ResponseBody
    public Map deleteDept(@RequestParam("targetid") String id){
        Map res=new HashMap<>();
        System.out.println("要删除的部门对象是:"+id);
        try{
            if(biz.deleteDepartment(id)){
                System.out.println("删除成功!");
                res.put("isOK",true);
                res.put("msg","删除成功!");
            }else{
                System.out.println("删除失败!");
                res.put("isOK",false);
                res.put("msg","删除失败");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

}

