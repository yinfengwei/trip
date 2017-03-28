package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.LocationDao;
import com.yin.trip.admin.entity.Location;
import com.yin.trip.admin.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationDao locationDao;

    /**
     * 插入登录位置记录
     *
     * @param location
     */
    @Override
    public void insertLocation(Location location) {

        locationDao.insertLocation(location);
    }
}
