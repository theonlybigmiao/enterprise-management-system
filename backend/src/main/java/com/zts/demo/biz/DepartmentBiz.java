package com.zts.demo.biz;
import com.zts.demo.entity.Department;
import com.zts.demo.mapper.DepartmentMapper;
import com.zts.demo.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentBiz {
    @Autowired
    private DepartmentMapper mapper;

    public DepartmentMapper getMapper() {
        return mapper;
    }

    public void setMapper(DepartmentMapper mapper) {
        this.mapper = mapper;
    }

    public Department selectByKey(String key){
        Department dept=mapper.selectByKey(key);
        if(dept!=null){
            return dept;
        }else{
            throw new MyException("查不到对应的部门");
        }
    }

    public Department selectByDepName(String name){
        Department dept=mapper.selectByDepName(name);
        if(dept!=null){
            return dept;
        }else{
            throw new MyException("查不到对应的部门");
        }
    }


    public int addDepartment(Department dept){
        System.out.println("addCeUser开始调用");
        try {
            int flag = mapper.insertDep(dept);
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

    public boolean updateDepartment(Department dept){
        try {
            int res = mapper.updateByKey(dept);
            System.out.println("Biz处的update执行，结果是:" + res);
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，例如记录日志或抛出运行时异常
            return false;
        }
    }

    public boolean deleteDepartment(String id){
        try{
            System.out.println("DepartmentBiz处的delete执行开始");
            int res=mapper.deleteById(id);
            System.out.println("DepartmentBiz处的delete执行，结果是:"+res);
            return res>0;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Department> getDepartmentList(){
        return mapper.selectAllDepartments();
    }

}

