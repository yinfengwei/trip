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
    public String page(String error, String info, ModelMap map,HttpSession session){

        map.addAttribute("info", info);
        map.addAttribute("error", error);


        if(session.getAttribute("userName") != null) {
            map.addAttribute("userName", session.getAttribute("userName") );
        }

        return "main";
    }
}
