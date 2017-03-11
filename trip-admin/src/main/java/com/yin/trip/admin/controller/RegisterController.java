package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/register")
    public String page(String error, String info, ModelMap map){
        map.addAttribute("error", error);
        map.addAttribute("info", info);
        return "register";
    }

    //登录操作
    @RequestMapping("/insertUser")
    public String insertUser(User user, ModelMap map) {

        //插入成功
        if (userService.insertUser(user)) {
            logger.info("用户注册成功");

            map.addAttribute("info", "注册成功");
            return "login";
        } else {
            logger.warn("用户注册失败");

            map.put("error","注册失败");
            return "register";
        }

    }
}
