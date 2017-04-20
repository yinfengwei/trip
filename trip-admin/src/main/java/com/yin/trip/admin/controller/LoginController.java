package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.Distance;
import com.yin.trip.admin.service.SightService;
import com.yin.trip.admin.service.UserService;
import com.yin.trip.common.entity.BaiDuLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/10 0010.
 * 登录控制
 */
@Controller

public class LoginController {

    @Autowired
    private UserService userService;


    @Autowired
    private SightService sightService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //进入页面
    @RequestMapping("/login")
    public String page(String error, String info, String lon, String lat,
                       ModelMap map, HttpSession session){
        map.addAttribute("error", error);
        map.addAttribute("info", info);

        //判断session 中是否已经包含经纬度等信息
        if(session.getAttribute("longitude") == null
                && session.getAttribute("latitude") == null) {

            //如果为空的话默认为深圳大学
            if (StringUtils.isEmpty(lon) || lon.equals("4.9E-324") || StringUtils.isEmpty(lat) || lat.equals("4.9E-324")) {
                lon = "113.937405";
                lat = "22.532493";
            }

            map.addAttribute("error", error);
            map.addAttribute("info", info);
            map.addAttribute("lon", lon);
            map.addAttribute("lat", lat);

            //获取景点距离数据并保存
            BaiDuLocation location = new BaiDuLocation();

            location.setLat(Double.parseDouble(lat));
            location.setLng(Double.parseDouble(lon));

            List<Distance> result = sightService.getDistance(location);

            Map<String, String> distances = new HashMap<String, String>();

            //保留一位小数
            for (Distance distance : result) {
                distances.put(distance.getSight().getName(), String.format("%.1f", distance.getDistance() / 1000));
            }

            Map<String, Double> locationScore = sightService.getDistanceScore(result);


            session.setAttribute("distance", result);
            session.setAttribute("distanceMap", distances);
            session.setAttribute("locationScore", locationScore);

            //传递位置信息保存至session
            session.setAttribute("longitude", lon);
            session.setAttribute("latitude", lat);
        }

        return "login";
    }

    //登录操作
    @RequestMapping("/check")
    public String login(String userName, String password, ModelMap map, HttpSession session) {

        if (userService.checkUserPassword(userName, password)){

            map.addAttribute("info", "登录成功,欢迎" + userName + "用户");
            session.setAttribute("userName",null);
            session.setAttribute("userName",userName);
            map.addAttribute("userName", userName);

            logger.info("登录成功");

            return "main";

        } else {

            map.addAttribute("error", "用户不存在或者密码不正确");
            logger.warn("用户不存在或者密码不正确");
            return "login";
        }
    }

    //注销登录
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session,ModelMap map) {

        map.addAttribute("info", "注销成功");

        if (session == null) {
            return "/login";
        }

        session.setAttribute("userName",null);

        return "/login";
    }

}
