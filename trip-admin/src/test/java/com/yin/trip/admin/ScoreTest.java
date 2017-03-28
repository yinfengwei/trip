package com.yin.trip.admin;

import com.yin.trip.admin.entity.Score;
import com.yin.trip.admin.service.ScoreService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

}
