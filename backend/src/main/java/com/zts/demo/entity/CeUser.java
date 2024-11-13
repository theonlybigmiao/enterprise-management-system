package com.zts.demo.entity;

import com.zts.demo.util.MyUtil;

public class CeUser {
    String pwd;
    String usm="新用户";
    String phone="00000000000";
    String gender="男";
    String email="待填写";
    String date;
    String role;
    String ent="测盟汇";
    String dept="待填写";
    String id;

    public CeUser(String pwd, String date, String role, String ent) {
        this.pwd = pwd;
        this.date = date;
        this.role = role;
        this.ent = ent;
        this.id= MyUtil.generateCeUserKey();
    }

    public CeUser() {
    }

    @Override
    public String toString() {
        return "CeUser{" +
                "pwd='" + pwd + '\'' +
                ", usm='" + usm + '\'' +
                ", phoon='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", role='" + role + '\'' +
                ", ent='" + ent + '\'' +
                ", dept='" + dept + '\'' +
                ", id=" + id +
                '}';
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsm() {
        return usm;
    }

    public void setUsm(String usm) {
        this.usm = usm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
