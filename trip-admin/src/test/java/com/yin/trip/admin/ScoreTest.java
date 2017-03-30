package com.yin.trip.admin;

import com.yin.trip.admin.entity.Score;
import com.yin.trip.admin.service.ScoreService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;

import java.util.*;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public class ScoreTest extends BaseTest{

    @Autowired
    private ScoreService scoreService;

    @Test
    public void testInsert(){

        Score score = new Score();

        score.setUserName("yin");
        score.setUserType("student");
        score.setSightName("世界之窗");
        score.setSightType("主题公园");
        score.setTime(new Date());
        score.setScore(4);


        scoreService.insertScore(score);
    }

    /**
     *  用户倒排表
     */
    @Test
    public void testGetScoreList(){

        List<String> userNames = scoreService.getSimUserByName("mouse",3,5);

        Map<String, List<String>> result = scoreService.getScoreChart("mouse",3,5);

//        System.out.print(result.size());

        Set<Map.Entry<String, List<String>>> entries = result.entrySet();

        for (Map.Entry<String, List<String>> entry : entries) {
            System.out.println(entry.getKey()+":"+
                    entry.getValue());

        }
    }

    @Test
    public void testGetScore(){
        String userName = "mouse";

        List<String> list = scoreService.getSimUserByName(userName,3 ,5);

        for (String user : list) {
            System.out.println(user);
        }

    }

}
