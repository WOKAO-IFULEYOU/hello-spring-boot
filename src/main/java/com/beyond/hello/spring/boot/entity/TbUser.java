package com.beyond.hello.spring.boot.entity;

import javax.persistence.*;
import java.util.Date;

/*
 *
 * mybatis逆行生成的实体
 * 属性要与表一致
 * 因为JPA也使用，所以必须加上@Entity和@Table两个注解
 * 对应表中是主键的属性，要加@Id等的注解
 * 因为JPA使用，所以实体中必须要有无参数的构造函数函数，否则启动报错
 *
 */
@Entity
@Table(schema ="wangzuo",name="tb_user")
public class TbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private Date creatData;

    private Date updetaData;

    public TbUser() {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.creatData = creatData;
        this.updetaData = updetaData;
    }

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