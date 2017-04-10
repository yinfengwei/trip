package com.yin.trip.admin.entity;

/**
 * Created by yinfeng on 2017/4/8 0008.
 *
 *  距离实体类
 */
public class Distance {

    private Sight sight;        //景点
    private double distance;    //距离

    public Sight getSight() {
        return sight;
    }

    public void setSight(Sight sight) {
        this.sight = sight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
