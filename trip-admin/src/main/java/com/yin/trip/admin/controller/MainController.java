package com.yin.trip.admin.controller;

import com.yin.trip.common.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by yinfeng on 2017/3/10 0010.
 */
@Controller
public class MainController {
    //进入页面
    @RequestMapping("/main")
    public String page(String error, String info, String lon, String lat, String addr, ModelMap map, HttpSession session){

        //如果为空的话默认为深圳大学
        if (StringUtils.isEmpty(lon) || lon.equals("4.9E-324") || StringUtils.isEmpty(lat) || lat.equals("4.9E-324") ) {
            lon = "113.937405";
            lat = "22.532493";
            addr = "深圳大学西南区学生公寓3栋";
        }

        map.addAttribute("error", error);
        map.addAttribute("info", info);
        map.addAttribute("lon", lon);
        map.addAttribute("lat", lat);
        map.addAttribute("addr", addr);

        //传递位置信息保存至session

        session.setAttribute("addr",addr);

//        String url = "http://api.map.baidu.com/geocoder/v2/";
//        String param = "callback=renderReverse&output=json&pois=1&ak=caR16D6gFwi9voPC3H32RlXrNShv4Fvn"
//                + "&mcode=49:A8:2E:E4:F5:39:65:2D:6D:A4:00:EC:8F:01:35:83:E8:26:F1:01;com.yin.trip";
//
//        String result = HttpUtil.sendGet(url,param + "&location=" + lat + "," + lon);
//        System.out.print(result);

        return "main";
    }
}
