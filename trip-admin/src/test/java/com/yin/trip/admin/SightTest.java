package com.yin.trip.admin;

import com.yin.trip.admin.entity.Distance;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.service.SightService;
import com.yin.trip.common.entity.BaiDuLocation;
import com.yin.trip.common.util.BaiDuApi;
import jxl.Sheet;
import jxl.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springContext.xml"})
@Transactional
public class SightTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SightService sightService;

    @Test
    public void testInsertData(){

        Sight sight = new Sight();

        //初始化
        sight.setName("科普特生态农业园");
        sight.setScore(5);
        sight.setAddress("深圳市大鹏新区银滩路19号较场尾p1停车场对面");
        sight.setType("度假村");
        sight.setRank(0);
        sight.setPlayTime("");
        sight.setPhone("");
        sight.setWebsite("");
        sight.setOpenTime("无需门票。进入免费，农业园内活动项目另外收费。");
        sight.setTicket("");
        sight.setIntroduce("");
        sight.setTips("");
        sight.setImgs("");
        sight.setSum(2);
        sight.setUserScore(4.5f);
        sight.setLongitude("113.937405");
        sight.setLatitude("22.532493");
        sight.setSightType("运动相关");

        sightService.insertData(sight);

    }

    @Test
    public void testGetCount(){
        logger.info("总数：" + sightService.getCount() );
        logger.info("sportSight：" + sightService.getCountBySightType("sportSight") );
        logger.info("relaxSight：" + sightService.getCountBySightType("relaxSight") );
        logger.info("humanSight：" + sightService.getCountBySightType("humanSight") );
        logger.info("natureSight：" + sightService.getCountBySightType("natureSight") );
        logger.info("studySight：" + sightService.getCountBySightType("studySight") );
    }



    //测试获取数据
    @Test
    public void testGetData(){
        Sight sight = sightService.getSightByName("世界之窗");

        System.out.println(sight.getAddress());
    }

    //测试更新数据
    @Test
    public void testUpdateData(){

        sightService.update("世界之窗",123);

        Sight sight = sightService.getSightByName("世界之窗");

        System.out.println(sight.getRank());
    }

    //获取列表
    @Test
    public void testGetListData(){

        String sightType = "sportSight";
        List<Sight> list = sightService.getSights(0,10,sightType);

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }

    @Test
    public void testRecommend(){
        String userName = "mouse";

//        sightService.getRecommend(userName);
         sightService.getTopSimilar(sightService.getSimilar(userName),1);
    }

    @Test
    public void testGetDistance(){
        Sight sight = sightService.getSightByName("世界之窗");

        BaiDuLocation location = new BaiDuLocation();

        location.setLat(Double.parseDouble(sight.getLatitude()));
        location.setLng(Double.parseDouble(sight.getLongitude()));

        BaiDuLocation destination = new BaiDuLocation();

        destination.setLat(22.532493);
        destination.setLng(113.937405);

        logger.info(BaiDuApi.distance(location, destination) + "");


    }

    @Test
    public void testGetDistanceList(){
        BaiDuLocation destination = new BaiDuLocation();

        destination.setLat(22.532493);
        destination.setLng(113.937405);

        List<Distance> result = sightService.getDistance(destination);

        for(Distance distance : result) {
            logger.info(distance.getSight().getName() + ":" + distance.getDistance());
        }
    }

    @Test
    public void testGetDistanceScore(){
        BaiDuLocation destination = new BaiDuLocation();

        destination.setLat(22.532493);
        destination.setLng(113.937405);

        List<Distance> result = sightService.getDistance(destination);

        Map<String, Double> scores = sightService.getDistanceScore(result);

//        for(String sight : scores.keySet()) {
//            logger.info(sight + ":" + scores.get(sight));
//        }

//        for (Map.Entry<Sight, Double> entry : scores.entrySet()) {
//            logger.info("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
    }

    @Test
    public void testGetTime(){
        BaiDuLocation location = new BaiDuLocation();

        location.setLat(22.532493);
        location.setLng(113.937405);

        Sight sight = sightService.getSightByName("世界之窗");

        BaiDuLocation destination = new BaiDuLocation();

        destination.setLng(Double.parseDouble(sight.getLongitude()));
        destination.setLat(Double.parseDouble(sight.getLatitude()));

        String type = "driving";

        BaiDuApi.getTime(location, destination,type);



    }

    @Test
    public void testBelongSightType(){

        String name = "世界之窗";
        String sightType = "natureSight";

        assert sightService.belongSightType(name, sightType);
    }

//    @Test
//    public void testUpdateSightType(){
//
//        List<String> sportsSight = new ArrayList<String>();
//        List<String> natureSight = new ArrayList<String>();
//        List<String> humanSight = new ArrayList<String>();
//        List<String> leisureSight = new ArrayList<String>();
//
//        //运动相关总共有12个
//        sportsSight.add("户外运动");
//        sportsSight.add("其他运动场馆");
//        sportsSight.add("高尔夫球场");
//        sportsSight.add("保龄球");
//        sportsSight.add("滑雪场");
//        sportsSight.add("体育馆");
//        sportsSight.add("登山");
//        sportsSight.add("游泳馆");
//        sportsSight.add("游艇中心");
//        sportsSight.add("水上运动");
//        sportsSight.add("浮潜/潜水");
//        sportsSight.add("活动基地");
//
//        //自然景观有19个
//        natureSight.add("植物园");
//        natureSight.add("岛屿/半岛");
//        natureSight.add("森林");
//        natureSight.add("桥");
//        natureSight.add("山岳/山岭");
//        natureSight.add("自然保护区");
//        natureSight.add("海滨/沙滩");
//        natureSight.add("动物园");
//        natureSight.add("农场");
//        natureSight.add("园林");
//        natureSight.add("池塘");
//        natureSight.add("湿地");
//        natureSight.add("地标");
//        natureSight.add("礁石/岩石");
//        natureSight.add("河流");
//        natureSight.add("地质地貌");
//        natureSight.add("峡谷");
//        natureSight.add("湖泊");
//        natureSight.add("小镇");
//        natureSight.add("主题公园");
//        natureSight.add("民俗村");
//
//        //人文景观总共有19个
//        humanSight.add("古迹");
//        humanSight.add("乡村");
//        humanSight.add("寺庙");
//        humanSight.add("美术馆");
//        humanSight.add("现代建筑");
//        humanSight.add("科技馆");
//        humanSight.add("教堂");
//        humanSight.add("艺术馆");
//        humanSight.add("展览馆");
//        humanSight.add("博物馆");
//        humanSight.add("水利工程");
//        humanSight.add("雕像/雕塑");
//        humanSight.add("历史建筑");
//        humanSight.add("名人故居");
//        humanSight.add("古镇/古村");
//        humanSight.add("陵园/墓地");
//        humanSight.add("祠堂");
//        humanSight.add("古塔");
//        humanSight.add("画廊");
//
//        //休闲场所23个
//        leisureSight.add("度假村");
//        leisureSight.add("Spa");
//        leisureSight.add("音乐厅");
//        leisureSight.add("游戏/娱乐中心");
//        leisureSight.add("生活服务");
//        leisureSight.add("城市公园");
//        leisureSight.add("酒吧/俱乐部");
//        leisureSight.add("剧场/剧院");
//        leisureSight.add("电影院");
//        leisureSight.add("特色街区");
//        leisureSight.add("农家乐");
//        leisureSight.add("观景台");
//        leisureSight.add("其他休闲会所");
//        leisureSight.add("采摘");
//        leisureSight.add("水上乐园");
//        leisureSight.add("水族馆");
//        leisureSight.add("浴场");
//        leisureSight.add("温泉");
//        leisureSight.add("观光路线");
//        leisureSight.add("广场");
//
//        leisureSight.add("游乐场");
//
//
//        //其他都为学习类
//
//
//        //获取所有景点
//        List<Sight> sights = sightService.getSights();
//
//        for(Sight sight : sights) {
//            String type = sight.getType().trim();
//
//            if(sportsSight.contains(type)) {
//                sight.setSightType("sportSight");
//
//            } else if (natureSight.contains(type)) {
//                sight.setSightType("natureSight");
//
//            } else if (humanSight.contains(type)) {
//                sight.setSightType("humanSight");
//
//            }else if (leisureSight.contains(type)) {
//                sight.setSightType("relaxSight");
//            } else {
//                sight.setSightType("studySight");
//            }
//
//            sightService.updateAll(sight);
//            System.out.println(sight.getName() + ":" + sight.getType() + "," + sight.getSightType());
//        }
//
//    }



//    @Test
//    public void testUpdateLonAndLat(){
//
////        List<Sight> list = sightService.getSightsById(506);
//
//
//
////        System.out.println(location.getLng() + " ," + location.getLat());
//
////        for(Sight sight : list) {
//
//            Sight sight = sightService.getSightByName("罗湖文化公园");
//            //获取景点位置
////            BaiDuLocation location = BaiDuApi.translate(sight.getAddress());
//
//            sight.setLongitude("114.13310290803195");
//            sight.setLatitude("22.55175897294979");
//
//            sightService.updateAll(sight);
//
//            logger.info("更新" + sight.getName() + "位置信息成功");
//
////        }
//
//
//    }
//    //测试更新景点排名
//    @Test
//    public void testUpdateRank(){
//        try{
//            File file = new File("D:\\Users\\yinfeng\\Desktop\\paiming.xls");
//            InputStream in = new FileInputStream(file);
//            Workbook workbook = Workbook.getWorkbook(in);
//            //获取第一张Sheet表
//            Sheet sheet = workbook.getSheet(0);
//
//            //循环得到数据，从第二行开始
//            for(int rows = 1; rows < sheet.getRows(); rows++) {
//
//                sightService.update(sheet.getCell(0, rows).getContents().trim(),rows);
//
//                logger.info("成功更新第" + rows + "条数据");
//            }
//
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//
//    }
//
//    //测试时插入数据
//    @Test
//    public void testExplain(){
//        try{
//            File file = new File("D:\\Users\\yinfeng\\Desktop\\爬取数据整合.xls");
//            InputStream in = new FileInputStream(file);
//            Workbook workbook = Workbook.getWorkbook(in);
//            //获取第一张Sheet表
//            Sheet sheet = workbook.getSheet(0);
//
//
//            //循环得到数据，从第二行开始
//            for(int rows = 1; rows < sheet.getRows(); rows++) {
//                Sight sight = new Sight();
//
//                //初始化
//                //景点名字一定有
//                sight.setName(sheet.getCell(0, rows).getContents().trim());
//
//                //得分可能为空,数据库允许为空
//                if (!StringUtils.isEmpty(sheet.getCell(1, rows).getContents().trim())) {
//                    sight.setScore(Float.parseFloat(sheet.getCell(1, rows).getContents().trim()));
//                }
//
//                //地址不能为空
//                sight.setAddress(sheet.getCell(2, rows).getContents().trim());
//
//                //类型可能为空
//                sight.setType(sheet.getCell(3, rows).getContents().trim());
//
////                //排名可能为空
////                if (!StringUtils.isEmpty(sheet.getCell(4, rows).getContents().trim())) {
////                    sight.setScore(Integer.parseInt(sheet.getCell(4, rows).getContents().trim()));
////                }
////                sight.setRank();
//
//                //游玩时间
//                sight.setPlayTime(sheet.getCell(5, rows).getContents().trim());
//
//                //电话号码
//                sight.setPhone(sheet.getCell(6, rows).getContents().trim());
//
//                //官网
//                sight.setWebsite(sheet.getCell(7, rows).getContents().trim());
//
//                //开放时间
//                sight.setOpenTime(sheet.getCell(8, rows).getContents().trim());
//
//                //门票信息
//                sight.setTicket(sheet.getCell(9, rows).getContents().trim());
//
//                //简介
//                sight.setIntroduce(sheet.getCell(10, rows).getContents().trim());
//
//                //tips
//                sight.setTips(sheet.getCell(11, rows).getContents().trim());
//
//                //图片
//                sight.setImgs(sheet.getCell(12, rows).getContents().trim());
//
//
//                //抽奖总数
//                if (StringUtils.isEmpty(sheet.getCell(13, rows).getContents().trim())) {
//                    sight.setSum(0);
//
//                } else {
//                    sight.setSum(Integer.parseInt(sheet.getCell(13, rows).getContents().trim()));
//                }
//
//
//                sightService.insertData(sight);
//
//                logger.info("成功插入第" + rows + "条数据");
//            }
//
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//
//    }
}
