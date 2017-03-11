package com.yin.trip.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yinfeng on 2017/3/10 0010.
 */
@Controller
public class MainController {
    //进入页面
    @RequestMapping("/main")
    public String page( String error, String info, ModelMap map){
        map.addAttribute("error", error);
        map.addAttribute("info", info);

        return "main";
    }
}
