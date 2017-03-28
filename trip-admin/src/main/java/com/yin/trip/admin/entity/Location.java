package com.yin.trip.admin.entity;

import java.util.Date;

/**
 * Created by yinfeng on 2017/3/27 0027.
 *  用户登录地理位置记录表
 */
public class Location {
    private long id;
    private String userName;        //用户姓名
    private double longitude;       //经度
    private double latitude;        //纬度
    private Date time;              //登录时间

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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
