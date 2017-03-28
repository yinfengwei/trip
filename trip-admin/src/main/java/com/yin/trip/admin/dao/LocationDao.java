package com.yin.trip.admin.dao;

import com.yin.trip.admin.entity.Location;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public interface LocationDao {
    /**
     *  插入登录地理位置数据
     * @param location
     */
    void insertLocation(Location location);
}
