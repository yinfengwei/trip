package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by yinfeng on 2017/3/10 0010.
 * 登录控制
 */
@Controller

public class LoginController {

    @Autowired
    private UserService userService;

    //进入页面
    @RequestMapping("/login")
    public String page(@ModelAttribute("error") String error, ModelMap map){
        map.addAttribute("error", error);

        return "login";
    }

    //登录操作
    @RequestMapping("/check")
    public String login(String userName, String password,ModelMap map) {

        if (userService.checkUserPassword(userName, password)){
            map.addAttribute("info", "登录成功");
            map.addAttribute("name", userName);
            return "/main";
        } else {
            map.addAttribute("error", "用户不存在或者密码不正确");
            return "/login";
        }
    }





}
