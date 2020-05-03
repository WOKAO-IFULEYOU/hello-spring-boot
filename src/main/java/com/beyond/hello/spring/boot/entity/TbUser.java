package com.beyond.hello.spring.boot.entity;

import java.util.Date;

public class TbUser {
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private Date creatData;

    private Date updetaData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreatData() {
        return creatData;
    }

    public void setCreatData(Date creatData) {
        this.creatData = creatData;
    }

    public Date getUpdetaData() {
        return updetaData;
    }

    public void setUpdetaData(Date updetaData) {
        this.updetaData = updetaData;
    }
}