package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.entity.Score;
import com.yin.trip.admin.service.ScoreService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by yinfeng on 2017/4/29 0029.
 */
public class ScoreServiceImplTest extends BaseTest{

    @Autowired
    private ScoreService scoreService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void insertScore() throws Exception {
        Score score = new Score();

        score.setUserName("yin");
        score.setUserType("student");
        score.setSightName("世界之窗");
        score.setSightType("主题公园");
        score.setTime(new Date());
        score.setScore(4);

        scoreService.insertScore(score);
    }

    @Test
    public void getScoreChart() throws Exception {

        List<String> userNames = scoreService.getSimUserByName("yin",3,5);

        Map<String, List<String>> result = scoreService.getScoreChart("yin",userNames);

       logger.info(result.size() + "");

    }

    @Test
    public void getScoreList() throws Exception {

        List<String> userNames = scoreService.getSimUserByName("yin",3,5);

        Map<String, List<String>> result = scoreService.getScoreChart("yin",userNames);


        Set<Map.Entry<String, List<String>>> entries = result.entrySet();

        for (Map.Entry<String, List<String>> entry : entries) {
            logger.info(entry.getKey()+":"+
                    entry.getValue());

        }
    }

    @Test
    public void getSimUserByName() throws Exception {
        List<String> userNames = scoreService.getSimUserByName("yin",3,5);

        logger.info(userNames.size() + "");
    }

}