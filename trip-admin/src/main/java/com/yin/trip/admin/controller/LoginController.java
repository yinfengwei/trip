package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import com.yin.trip.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by yinfeng on 2017/3/10 0010.
 * 登录控制
 */
@Controller

public class LoginController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //进入页面
    @RequestMapping("/login")
    public String page(String error, String info,
                       ModelMap map){
        map.addAttribute("error", error);
        map.addAttribute("info", info);

        return "login";
    }

    //登录操作
    @RequestMapping("/check")
    public String login(String userName, String password, ModelMap map) {

        if (userService.checkUserPassword(userName, password)){

            map.addAttribute("info", "登录成功,欢迎" + userName + "用户");
            logger.info("登录成功");
            return "main";

        } else {

            map.addAttribute("error", "用户不存在或者密码不正确");
            logger.warn("用户不存在或者密码不正确");
            return "login";
        }
    }





}
