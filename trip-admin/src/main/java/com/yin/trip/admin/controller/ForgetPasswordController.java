package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.Result;
import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yinfeng on 2017/3/10 0010.
 */
@Controller
public class ForgetPasswordController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/forget")
    public String page(String error,String info, ModelMap map){
        map.addAttribute("error", error);
        map.addAttribute("info", info);
        return "forget";
    }
    //更新操作
    @RequestMapping("/update")
    public String update(User user, ModelMap map) {

        Result result = userService.updateUser(user);

        if (result.isResult()){
            map.addAttribute("info","更新成功");
            logger.info("更新成功");

            return "login";
        } else {
            map.addAttribute("error", result.getMessage());
            logger.warn("更新失败");
            return "forget";
        }
    }

}
