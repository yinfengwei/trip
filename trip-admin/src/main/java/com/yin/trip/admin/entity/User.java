package com.yin.trip.admin.entity;

/**
 * Created by yinfeng on 2017/3/10 0010.
 *  用户表实体类
 */
public class User {
    private long id;
    private String userName;        //用户名
    private String password;        //密码
    private int phone;              //手机号码
    private String type;            //用户类型
    private int age;                //年龄段
    private int sex;                //性别,0代表男，1代表女

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
