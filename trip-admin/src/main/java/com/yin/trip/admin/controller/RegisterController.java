package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yinfeng on 2017/3/10 0010.
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String page(@ModelAttribute("error") String error, ModelMap map){
        map.addAttribute("error", error);
        return "register";
    }

    //登录操作
    @RequestMapping("/insertUser")
    public String insertUser(User user, ModelMap map) {

        //插入成功
        if (userService.insertUser(user)) {
            map.addAttribute("info","注册成功");
            return "/login";
        } else {
            map.addAttribute("error","注册失败");
            return "/register";
        }

    }
}
