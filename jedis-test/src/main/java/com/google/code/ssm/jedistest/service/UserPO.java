package com.google.code.ssm.jedistest.service;

import java.io.Serializable;

/**
 * Created by Avalon on 2016/9/3.
 */
public class UserPO implements Serializable{
    private Integer uid;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public UserPO() {
    }

    public UserPO(Integer uid, String userName, String password) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "uid='" + uid + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
