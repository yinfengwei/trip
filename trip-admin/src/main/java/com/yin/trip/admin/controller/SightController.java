package com.yin.trip.admin.controller;

import com.yin.trip.admin.entity.Click;
import com.yin.trip.admin.entity.Distance;
import com.yin.trip.admin.entity.Score;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.service.ClickService;
import com.yin.trip.admin.service.ScoreService;
import com.yin.trip.admin.service.SightService;
import com.yin.trip.admin.service.UserService;

import com.yin.trip.common.entity.BaiDuLocation;
import com.yin.trip.common.util.BaiDuApi;
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
    public String page(String info, String error,String type, ModelMap modelMap,
                       String page, String sightType, HttpSession session) {

        modelMap.addAttribute("info", info);
        modelMap.addAttribute("error", error);

        //如果第一次进入或者景点类型有变化
        if (sightType != null) {
            session.setAttribute("sightType", sightType);
        } else {
            sightType = (String)session.getAttribute("sightType");
        }

        Map<String, String> distanceMap = (Map<String, String>)session.getAttribute("distanceMap");

        modelMap.addAttribute("distanceMap", distanceMap);


        if(session.getAttribute("userName") == null && type.equals("recommend")) {
            modelMap.addAttribute("error", "访客模式不支持综合推荐");
        }

        //访客模式不支持综合推荐,采用景点排名
        if(type.equals("rank") || (session.getAttribute("userName") == null
                && type.equals("recommend"))) {

            //推荐类型
            modelMap.addAttribute("type", "rank");

            //查到的总用户数
            int sum = sightService.getCountBySightType(sightType);
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

            //参数为偏移跟长度
            List<Sight> list = sightService.getSights(startRow, pageSize, sightType);

            modelMap.addAttribute("list", list);
            modelMap.addAttribute("currentPage", Integer.parseInt(page));
        } else if(type.equals("recommend")){

            //推荐类型
            modelMap.addAttribute("type", type);

            String userName = session.getAttribute("userName").toString();
            List<String> recommend;
            //页面初始的时候page没有值,获取推荐列表
            if (null == page) {
                page = "1";
            }
                //推荐
                Map<String, Double> locationScore = (Map<String, Double>)session.getAttribute("locationScore");
                List<Map.Entry<Sight, Double>> recommendSight = sightService.getRecommend(userName, locationScore, sightType);

                recommend = new ArrayList<String>();

                //添加推荐景点名保存至session中
                for (int i = 0 ; i < recommendSight.size(); i++) {

                    recommend.add(recommendSight.get(i).getKey().getName());
                }


//                session.setAttribute("recommend", recommend);
//            } else {
//
//                recommend = (ArrayList<String>)session.getAttribute("recommend");
//            }

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

            for(int j = 0; j < pageSize && startRow + j < sum; j++) {
                list.add(sightService.getSightByName(recommend.get(startRow + j)));
            }

            modelMap.addAttribute("list", list);
            modelMap.addAttribute("currentPage", Integer.parseInt(page));


        } else {        //按照景点位置从近到远
            //推荐类型
            modelMap.addAttribute("type", type);

            //获取景点从近到远
            List<Distance> distances = (List<Distance>)session.getAttribute("distance");

            List<Distance> result = new ArrayList<Distance>();

            //添加符合景点类型的数据
            for(Distance distance : distances) {
                if (distance.getSight().getSightType().equals(sightType)) {
                    result.add(distance);
                }
            }


            //查到的总用户数
            int sum = result.size();
            int pageSize = 15;//默认为15

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
            List<Sight> list = new ArrayList<Sight>();

            for (int i = 0; i < pageSize && startRow + i < sum; i++) {
                list.add(result.get(startRow + i).getSight());
            }

            modelMap.addAttribute("list", list);
            modelMap.addAttribute("currentPage", Integer.parseInt(page));

        }

        return "sight";
    }





    @RequestMapping("/view")
    public String viewSight(String name, ModelMap modelMap, HttpSession session){

        Sight sight = sightService.getSightByName(name);
        Sight sessionSight = (Sight)session.getAttribute("sight");


        //获取相关评论
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("sightName", name);
        param.put("offset",0);
        param.put("length",5);

        List<Score> comments = scoreService.getScoreListWithComment(param);

        if (comments.size() > 0) {
            modelMap.addAttribute("comments", comments);
        }



        //如果为访客模式则不记录
        if (session.getAttribute("userName") != null) {

            String userName = session.getAttribute("userName").toString();

            //判断用户是否已经评分

            param.put("userName", userName);

            List<Score> scores = scoreService.getScoreList(param);

            if (scores.size() != 0) {
                modelMap.put("score",scores.get(0).getScore());
            }

            //判断是否重复进行点击
            if (sessionSight != null && sight.getName().equals(sessionSight.getName())) {

                logger.info("重复点击，不记录进入数据库");

            } else {


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

            String userName = session.getAttribute("userName").toString();

            //插入点击记录
            Score insertScore = new Score();

            //判断是否已经存在评分数据，存在则进行更新操作
            //判断用户是否已经评分
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userName", userName);
            paramMap.put("sightName", sight.getName());

            List<Score> scores = scoreService.getScoreList(paramMap);

            if (scores.size() != 0) {
                //复制评论
                insertScore.setComment(scores.get(0).getComment());
            }



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

//    @RequestMapping("/comment")
//    @ResponseBody
//    public String comment(String comment, HttpSession session){
//
//        //判断操作是否成功
//        String success = "";
//
//        try {
//            if (session.getAttribute("userName") == null) {
//                success = "2";
//                logger.info("插入评论失败: 用户未登录");
//            } else {
//
//                Sight sight = (Sight)session.getAttribute("sight");
//
//                String userName = session.getAttribute("userName").toString();
//
//
//                //判断用户是否已经评分
//                Map<String, Object> param = new HashMap<String, Object>();
//
//                param.put("userName", userName);
//                param.put("sightName", sight.getName());
//
//
//                List<Score> scores = scoreService.getScoreList(param);
//                logger.info("获取数据为" + scores);
//                logger.info("获取数据大小为" + scores.size());
//
//                if (scores == null || scores.size() == 0) {
//                    success = "3";
//                    logger.info("插入评论失败: 用户未进行评分");
//                } else {
//                    success = "1";
//
//                    Score tempScore = scores.get(0);
//
//                    tempScore.setComment(comment);
//
//                    scoreService.update(tempScore);
//                    logger.info("插入用户评分记录成功");
//                }
//            }
//
//            return success;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "4";
//        }
//
//    }

    @RequestMapping("/comment")
    public String comment(String comment,ModelMap modelMap,  HttpSession session){

        Sight sight = (Sight)session.getAttribute("sight");


        if (session.getAttribute("userName") == null) {

            modelMap.put("error","请先登录再进行评论");

            logger.info("插入评论失败: 用户未登录");

        } else {

            String userName = session.getAttribute("userName").toString();

            //判断用户是否已经评分
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userName", userName);
            paramMap.put("sightName", sight.getName());

            List<Score> scores = scoreService.getScoreList(paramMap);
            logger.info("获取数据为" + scores);
            logger.info("获取数据大小为" + scores.size());

            if (scores.size() == 0) {

                modelMap.put("error","请先评分再进行评论");
                logger.info("插入评论失败: 用户未进行评分");

            } else {

                Score tempScore = scores.get(0);

                tempScore.setComment(comment);

                scoreService.update(tempScore);
                modelMap.put("info","评论成功");
                modelMap.put("score",tempScore.getScore());

                logger.info("插入用户评分记录成功");

            }
        }

        //获取相关评论
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("sightName", sight.getName());
        param.put("offset",0);
        param.put("length",5);

        List<Score> comments = scoreService.getScoreListWithComment(param);

        if (comments.size() > 0) {
            modelMap.addAttribute("comments", comments);
        }

        return "view";

    }

    @RequestMapping("/getTime")
    @ResponseBody
    public List<String> getTime(double latitude,double longitude,double localLatitude,double localLongitude){

        List<String> types = new ArrayList<String>();
        List<String> result = new ArrayList<String>();

        types.add("driving");        //驾车
        types.add("walking");        //步行
        types.add("transit");        //公交

        BaiDuLocation location = new BaiDuLocation();
        BaiDuLocation destination = new BaiDuLocation();

        location.setLat(localLatitude);
        location.setLng(localLongitude);

        destination.setLat(latitude);
        destination.setLng(longitude);

        for(String type : types) {
            result.add(BaiDuApi.getTime(location, destination, type));
        }

        return result;
    }

    @RequestMapping("/go")
    public String goSight(String name, ModelMap modelMap, HttpSession session) {

        Sight sight = sightService.getSightByName(name);
        modelMap.put("sight", sight);

        //是否登录
        if (session.getAttribute("userName") != null) {
            modelMap.addAttribute("userName", session.getAttribute("userName").toString());
        }


        return "/go";
    }

}



