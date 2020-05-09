package com.beyond.hello.spring.boot.model;

/*
 * 返回页面用数据的实体
 */
public class UserModel {
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String creatData;

    private String updetaData;

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
        this.name = name;
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

    public String getCreatData() {
        return creatData;
    }

    public void setCreatData(String creatData) {
        this.creatData = creatData;
    }

    public String getUpdetaData() {
        return updetaData;
    }

    public void setUpdetaData(String updetaData) {
        this.updetaData = updetaData;
    }
}
