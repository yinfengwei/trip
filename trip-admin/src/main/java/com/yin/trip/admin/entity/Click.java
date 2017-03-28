package com.yin.trip.admin.entity;

import java.util.Date;

/**
 * Created by yinfeng on 2017/3/27 0027.
 *  用户点击记录表
 */
public class Click {
    private long id;
    private String userName;        //用户名
    private String userType;           //用户类型
    private String sightName;       //景点类型
    private Date time;                //点击名
    private String sightType;        //景点时间

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSightName() {
        return sightName;
    }

    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public String getSightType() {
        return sightType;
    }

    public void setSightType(String sightType) {
        this.sightType = sightType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
