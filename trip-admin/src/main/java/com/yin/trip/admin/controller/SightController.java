package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.Click;
import com.yin.trip.admin.entity.Score;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.service.ClickService;
import com.yin.trip.admin.service.ScoreService;
import com.yin.trip.admin.service.SightService;
import com.yin.trip.admin.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


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

    @Autowired
    private ClickService clickService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService userService;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping
    public String page(String info, String error, ModelMap modelMap,
                       String page, HttpSession session) {

        modelMap.addAttribute("info", info);
        modelMap.addAttribute("error", error);



        //访客模式
        if(session.getAttribute("userName") == null) {

            //查到的总用户数
            int sum = sightService.getCount();
            int pageSize = 15;//默认为20

            modelMap.addAttribute("sum", sum);

            //总页数
            int pageTimes;
            if (sum % pageSize == 0) {
                pageTimes = sum / pageSize;
            } else {
                pageTimes = sum / pageSize + 1;
            }

            modelMap.addAttribute("pageTimes", pageTimes);

            //页面初始的时候page没有值
            if (null == page) {
                page = "1";
            }

            //每页开始的第几条记录
            int startRow = (Integer.parseInt(page) - 1) * pageSize;

            //排名不能为0
            List<Sight> list = sightService.getSights(startRow + 1, startRow + pageSize);

            modelMap.addAttribute("list", list);
            modelMap.addAttribute("currentPage", Integer.parseInt(page));
        } else {

            String userName = session.getAttribute("userName").toString();
            List<String> recommend;
            //页面初始的时候page没有值,获取推荐列表
            if (null == page) {
                page = "1";
                //推荐
                List<Map.Entry<Sight, Double>> recommendSight = sightService.getRecommend(userName);

                recommend = new ArrayList<String>();

                //添加推荐景点名保存至session中
                for (int i = 0 ; i < recommendSight.size(); i++) {

                    recommend.add(recommendSight.get(i).getKey().getName());
                }


                session.setAttribute("recommend", recommend);
            } else {

                recommend = (ArrayList<String>)session.getAttribute("recommend");
            }

            //查到的总用户数
            int sum = recommend.size();
            int pageSize = 15;//默认为20

            modelMap.addAttribute("sum", sum);

            //总页数
            int pageTimes;
            if (sum % pageSize == 0) {
                pageTimes = sum / pageSize;
            } else {
                pageTimes = sum / pageSize + 1;
            }

            modelMap.addAttribute("pageTimes", pageTimes);



            //每页开始的第几条记录
            int startRow = (Integer.parseInt(page) - 1) * pageSize;

            //排名不能为0
            List<Sight> list = new ArrayList<Sight>();

            for(int j = 0; j < pageSize ; j++) {
                list.add(sightService.getSightByName(recommend.get(startRow + j)));
            }

            modelMap.addAttribute("list", list);
            modelMap.addAttribute("currentPage", Integer.parseInt(page));


        }

        return "sight";
    }




    @ResponseBody
    @RequestMapping("/appendSight")
    public List<Sight> appendSight(String currentPage) {

        System.out.print(currentPage);
        int page = Integer.parseInt(currentPage);

        //每页开始的第几条记录
        int startRow = (page - 1) * 10;

        return sightService.getSights(startRow + 1, startRow + 10);

    }

    @RequestMapping("/view")
    public String viewSight(String name, ModelMap modelMap, HttpSession session){

        Sight sight = sightService.getSightByName(name);
        Sight sessionSight = (Sight)session.getAttribute("sight");




        //如果为访客模式则不记录
        if (session.getAttribute("userName") != null) {

            //判断是否重复进行点击
            if (sessionSight != null && sight.getName().equals(sessionSight.getName())) {

                logger.info("重复点击，不记录进入数据库");

            } else {

                String userName = session.getAttribute("userName").toString();
                //插入点击记录
                Click click = new Click();


                click.setUserName(userName);
                //根据用户名查找用户类型
                click.setUserType(userService.getUserByUseName(userName).getType());
                click.setSightName(name);
                click.setSightType(sight.getType());
                click.setTime(new Date());

                clickService.insertClick(click);
                modelMap.addAttribute("userName", userName);

                logger.info("记录点击数据成功");
            }
        }

        modelMap.addAttribute("sight", sight);

        session.setAttribute("sight", sight);

        return "view";

    }

    @RequestMapping("/score")
    @ResponseBody
    public String score(String score, HttpSession session){


        //判断操作是否成功
         String success = "";

        try {
            Sight sight = (Sight)session.getAttribute("sight");

            //插入点击记录
            Score insertScore = new Score();

            String userName = session.getAttribute("userName").toString();

            insertScore.setUserName(userName);
            //根据用户名查找用户类型
            insertScore.setUserType(userService.getUserByUseName(userName).getType());
            insertScore.setSightName(sight.getName());
            insertScore.setSightType(sight.getType());
            insertScore.setTime(new Date());
            insertScore.setScore(Integer.parseInt(score));

            scoreService.insertScore(insertScore);
            logger.info("插入用户评分记录成功");

            success = "1";

        } catch (Exception e) {
            e.printStackTrace();
            success = "0";
        } finally {

            return success;
        }

    }

    @RequestMapping("/go")
    public String goSight(String name, ModelMap modelMap) {

        Sight sight = sightService.getSightByName(name);

        return "/go";
    }

}



