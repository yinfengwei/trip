package com.yin.trip.admin;

import com.yin.trip.admin.entity.Location;
import com.yin.trip.admin.service.LocationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public class LocationTest extends BaseTest {

    @Autowired
    private LocationService locationService;

    @Test
    public void testInsertData(){
        Location location = new Location();

        //初始化

        location.setUserName("yin");
        location.setLongitude(113.937405);
        location.setLatitude(22.532493);
        location.setTime(new Date());

        locationService.insertLocation(location);
    }

}
