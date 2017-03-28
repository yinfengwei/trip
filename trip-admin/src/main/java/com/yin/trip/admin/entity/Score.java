package com.yin.trip.admin.entity;

import java.util.Date;

/**
 * Created by yinfeng on 2017/3/27 0027.
 *  用户评分表
 */
public class Score {

    private long id;
    private String userName;        //用户名
    private String userType;          //用户类型
    private String sightName;       //景点名
    private String sightType;       //景点类型
    private int score;              //用户评分
    private Date time;              //评分时间

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
