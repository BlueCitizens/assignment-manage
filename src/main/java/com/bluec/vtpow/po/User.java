package com.bluec.vtpow.po;

import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/10 22:10
 */
@JSONType(orders = {"stu_id","username","password"})
public class User implements Serializable {
    String stu_id;
    String username;
    String password;

    public User() {
    }

    public User(String stu_id, String username, String password) {
        this.stu_id = stu_id;
        this.username = username;
        this.password = password;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "stu_id='" + stu_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
