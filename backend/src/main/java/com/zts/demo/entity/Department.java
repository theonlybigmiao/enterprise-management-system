package com.zts.demo.entity;

import com.zts.demo.util.MyUtil;

public class Department {
    String name;
    String superior="DEaaaaa";
    int priority=1;
    String head;
    String phone;
    String email;
    String state="正常";
    String date;
    String id;

    public Department() {
    }

    public Department(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.date = MyUtil.getCurrentTime();
        this.id = MyUtil.generateDeUserKey();

    }

    public Department(String name, String superior, int priority, String head, String phone, String email, String state) {
        this.name = name;
        this.superior = superior;
        this.priority = priority;
        this.head = head;
        this.phone = phone;
        this.email = email;
        this.state = state;
        this.date = MyUtil.getCurrentTime();
        this.id = MyUtil.generateDeUserKey();
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", superior='" + superior + '\'' +
                ", priority='" + priority + '\'' +
                ", head='" + head + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", date='" + date + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
