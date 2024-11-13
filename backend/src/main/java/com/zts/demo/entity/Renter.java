package com.zts.demo.entity;

import com.zts.demo.util.MyUtil;

public class Renter {
    String key;
    String ent;
    String phone;
    String pwd;
    public Renter(String ent, String phone, String password) {
        this.ent = ent;
        this.phone = phone;
        this.pwd = password;
        this.key= MyUtil.generateRenterKey();
    }

//    public int getNumber(){
//        return Renter.number;
//    }

    public Renter() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String password) {
        this.pwd = password;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "key=" + key +
                ", emp='" + ent + '\'' +
                ", phoon='" + phone + '\'' +
                ", password='" + pwd + '\'' +
                '}';
    }
}
