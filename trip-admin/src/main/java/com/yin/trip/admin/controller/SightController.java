package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by yinfeng on 2017/3/18 0018.
 *  景点控制类
 */
@Controller
@RequestMapping("/sight")
@SessionAttributes("userName")
public class SightController {

    @Autowired
    private SightService sightService;

    @RequestMapping
    public String page(String info, String error, ModelMap modelMap,
                       String page) {

        modelMap.addAttribute("info", info);
        modelMap.addAttribute("error", error);


        //查到的总用户数
        int sum = sightService.getCount();
        int pageSize = 10;//默认为10

        modelMap.addAttribute("sum", sum);

        //总页数
        int pageTimes;
        if (sum % pageSize == 0) {
            pageTimes = sum/pageSize;
         }else {
            pageTimes = sum/pageSize + 1;
         }

        modelMap.addAttribute("pageTimes", pageTimes);

        //页面初始的时候page没有值
        if(null == page) {
            page = "1";
        }

        //每页开始的第几条记录
        int startRow = (Integer.parseInt(page)-1) * pageSize;

        //排名不能为0
        List<Sight> list = sightService.getSights(startRow + 1, startRow + pageSize);

        modelMap.addAttribute("list", list);
        modelMap.addAttribute("currentPage", Integer.parseInt(page));

        return "sight";
    }

    @RequestMapping("/view")
    public String viewSight(String name,ModelMap modelMap){

        Sight sight = sightService.getSightByName(name);

        modelMap.addAttribute("sight", sight);

        return "view";

    }






}
