package com.yin.trip.admin;

import com.yin.trip.admin.entity.Distance;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.service.SightService;
import com.yin.trip.common.entity.BaiDuLocation;
import com.yin.trip.common.util.BaiDuApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

        sightService.insertData(sight);

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

        List<Sight> list = sightService.getSights(1,10);

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }

    @Test
    public void testRecommend(){
        String userName = "yin";

//        sightService.getRecommend(userName);
        sightService.getSimilar(userName);
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
