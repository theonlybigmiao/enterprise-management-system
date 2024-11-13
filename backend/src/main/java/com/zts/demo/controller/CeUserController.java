package com.zts.demo.controller;
import com.zts.demo.biz.CeUserBiz;
import com.zts.demo.entity.CeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@Controller
@ResponseBody
public class CeUserController {
    @Autowired
    private CeUserBiz biz;
    public static CeUser myceuser;
    public void setBiz(CeUserBiz biz){
        this.biz=biz;
    }
    @RequestMapping("/ceuser/login")
//    路由
    @ResponseBody
    public Map Login(@RequestParam("flag") String flag, @RequestParam("ones") String ones,@RequestParam("password") String password,HttpServletResponse response){
//        通过@RequestParam接收前端参数
        CeUser ceuser=new CeUser();
        Map map=new HashMap<>();
        try{
            ceuser=flag.equals("企业管理员")?biz.checkEntLogin(ones,password):biz.checkAdmLogin(ones,password);
//            登录验证
        }catch (Exception e){
            e.printStackTrace();
        }
        String token = ceuser.getId();
        map.put("token", token);
//        设置拦截器和记住密码的cookie和token
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        cookie.setSecure(true);
        response.addCookie(cookie);
        myceuser=ceuser;
        map.put("isOK",true);
        map.put("userrole",ceuser.getRole());
        return map;
    }

    @RequestMapping("/ceuser/show")
    @ResponseBody
    public Map findAll(){
        Map map=new HashMap<>();
        map.put("isOK",true);
        map.put("myuser",myceuser);
        return map;
    }

    @RequestMapping("/ceuser/uploadAvatar")
    @ResponseBody
    public Map uploadAvatar(MultipartFile file){
//        System.out.println("开始执行");
        String filename=file.getOriginalFilename();

        System.out.println(filename);
        Map map=new HashMap<>();
        if(filename!=null){
            map.put("isOK",true);
        }
        else{
            map.put("isOK",false);
        }

        return map;
    }


    @RequestMapping("/ceuser/updateCeUserAccount")
    @ResponseBody
    public Map updateUser(@RequestParam("usm") String usm,@RequestParam("phone") String phone,@RequestParam("gender") String gender,
                          @RequestParam("email") String email,@RequestParam("dept") String dept){
        Map res=new HashMap<>();
//        System.out.println("要修改的对象是:"+myceuser.getId());
//        System.out.println("要修改的昵称是:"+usm);
//        System.out.println("要修改的性别是:"+gender);
        CeUser newceuser=new CeUser();
        newceuser.setEmail(email);
        newceuser.setDept(dept);
        newceuser.setId(myceuser.getId());
        newceuser.setGender(gender);
        newceuser.setUsm(usm);
        newceuser.setPhone(phone);
       try{
           if(biz.updateCeUserAccount(newceuser)){
               myceuser.setEmail(email);
               myceuser.setDept(dept);
               myceuser.setId(myceuser.getId());
               myceuser.setGender(gender);
               myceuser.setUsm(usm);
               myceuser.setPhone(phone);
               System.out.println("修改资料成功!");
               res.put("isOK",true);
               res.put("msg","修改资料成功!");
           }else{
               System.out.println("修改资料失败!");
               res.put("isOK",false);
               res.put("msg","修改资料失败");
           }
       }catch(Exception e){
           e.printStackTrace();
       }
        return res;
    }

    @RequestMapping("/ceuser/updateCeUserPassWord")
    @ResponseBody
    public Map updateUserPwd(@RequestParam("newpwd") String newpwd){
        Map res=new HashMap<>();
        System.out.println("要修改的密码是:"+myceuser.getPwd());
        System.out.println("新密码是:"+newpwd);
        try{
            if(biz.updateCeUserPassword(newpwd, myceuser.getId())){
                myceuser.setPwd(newpwd);
                System.out.println("修改密码成功!");
                res.put("isOK",true);
                res.put("msg","修改密码成功!");
            }else{
                System.out.println("修改密码失败!");
                res.put("isOK",false);
                res.put("msg","修改密码失败");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("/ceuser/logout")
    @ResponseBody
    public Map Logout(HttpSession session){
        Map res=new HashMap<>();
        session.invalidate();
        System.out.println("可以安全退出");
        res.put("isOK",true);
        res.put("msg","已安全退出");
        return res;
    }

}
