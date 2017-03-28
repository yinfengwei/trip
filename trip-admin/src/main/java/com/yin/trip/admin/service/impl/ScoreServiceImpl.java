package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.ScoreDao;
import com.yin.trip.admin.entity.Score;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.service.ScoreService;
import com.yin.trip.admin.service.SightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
@Service
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private SightService sightService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 增加评分记录
     *
     * @param score
     */
    @Override
    public void insertScore(Score score) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("userName", score.getUserName());
        param.put("sightName", score.getSightName());

        Sight sight = sightService.getSightByName(score.getSightName());

        Score tempScore = scoreDao.getScore(param);


        //判断数据库中是否已经含有该记录
        if (tempScore != null) {

            logger.info("数据库中已经含有该类型记录，进行更新记录");

            scoreDao.updateScore(score);

            logger.info("更新数据成功");

            //更新数据，除去原本的得分，重新添加得分
            float newScore = (sight.getUserScore() * sight.getUserSum()
                    - tempScore.getScore() + score.getScore())/sight.getUserSum();
            sightService.updateScore(score.getSightName(),newScore, sight.getUserSum());

            logger.info("更新景点平均评分数据成功");

        } else {
            scoreDao.insertScore(score);

            logger.info("插入记录成功");

            if (sight.getUserSum() == 0) {

                sightService.updateScore(score.getSightName(),score.getScore(), 1L);

                logger.info("插入评分成功");
            }  else {

                //更新数据，添加得分
                float newScore = (sight.getUserScore() * sight.getUserSum()
                        + score.getScore())/ (sight.getUserSum() + 1);
                sightService.updateScore(score.getSightName(),newScore, sight.getUserSum() + 1);
                logger.info("添加评分成功");
            }
        }


    }
}
