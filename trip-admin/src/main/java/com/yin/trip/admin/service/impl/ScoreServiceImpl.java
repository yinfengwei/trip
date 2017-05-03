package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.ScoreDao;
import com.yin.trip.admin.entity.Score;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.service.ScoreService;
import com.yin.trip.admin.service.SightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        param.put("sightName", score.getSightName());

        //获取该景点的所有评分
        List<Score> scores = scoreDao.getScore(param);

        param.put("userName", score.getUserName());

        Sight sight = sightService.getSightByName(score.getSightName());

        List<Score> tempScore = scoreDao.getScore(param);


        int sum = 0;
        //计算总得分
        for(Score score1 : scores) {
            sum += score1.getScore();
        }

        //判断数据库中是否已经含有该记录
        if (tempScore.size() != 0) {

            logger.info("数据库中已经含有该类型记录，进行更新记录");

            scoreDao.updateScore(score);

            logger.info("更新数据成功");

            //更新数据，获取所有该景点的得分并重新覆盖


            float newScore = (sum - tempScore.get(0).getScore() + score.getScore())/sight.getUserSum();

            //保留为一位小数
            sightService.updateScore(score.getSightName(),Float.parseFloat(String.format("%.1f",newScore)), sight.getUserSum());

            logger.info("更新景点平均评分数据成功");

        } else {
            scoreDao.insertScore(score);

            logger.info("插入记录成功");

            if (sight.getUserSum() == 0) {

                sightService.updateScore(score.getSightName(),score.getScore(), 1L);

                logger.info("插入评分成功");
            }  else {

                //更新数据，添加得分
                float newScore = (sum + score.getScore())/ (sight.getUserSum() + 1);

                //保留一位小数
                sightService.updateScore(score.getSightName(),Float.parseFloat(String.format("%.1f",newScore)), sight.getUserSum() + 1);
                logger.info("添加评分成功");
            }
        }
    }

    /**
     * 获取评分用户倒查表
     */
    @Override
    public Map<String, List<String>> getScoreChart(String name ,List<String> usersName) {

        //获取相关的用户列表
        Map<String, Object> param = new HashMap<String, Object>();

        //倒排表需要分析的用户数据
//        List<String> usersName = getSimUserByName(name,startScore,endScore);
        usersName.add(name);

        param.put("usersName", usersName);

        //倒查表，遍历所有评分数据。获得评分表
        List<Score> list = scoreDao.getScoreList(param);

        Map<String, List<String>> result = new HashMap<String, List<String>>();



        //添加第一个物品-用户数据

//        users.add(list.get(0).getUserName());
//        result.put(list.get(0).getSightName(),users);


        for (int i = 0; i < list.size(); i++) {

            String sightName = list.get(i).getSightName();
            String userName = list.get(i).getUserName();

            List<String> tempUsers;
            //如果数据库中已经存在该景点，则添加list
            if (result.containsKey(sightName)) {
                tempUsers = result.get(sightName);

                //如果列表中已经包含则不进行操作
                if (!tempUsers.contains(userName)) {
                    tempUsers.add(userName);
                }

            } else {

                //创建新的用户表
                tempUsers = new ArrayList<String>();

                tempUsers.add(userName);
            }

            result.put(sightName, tempUsers);
        }



        return result;
    }

    /**
     * 根据参数获取数据
     *
     * @param param
     */
    @Override
    public List<Score> getScoreList(Map<String, Object> param) {

        return  scoreDao.getScoreList(param);
    }

    /**
     * 根据参数获取评论数据
     *
     * @param param
     */
    @Override
    public List<Score> getScoreListWithComment(Map<String, Object> param) {
        return scoreDao.getScoreWithComment(param);
    }

    /**
     * 更新数据
     *
     * @param score
     */
    @Override
    public void update(Score score) {
        scoreDao.updateScore(score);
    }

    /**
     * 通过用户名获取与该用户去过同个景点的用户列表,且满足评分限制，[startScore, endScore]
     *
     * @param userName
     * @return
     */
    @Override
    public List<String> getSimUserByName(String userName,int startScore, int endScore) {

        //获取该用户去过的景点列表
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userName", userName);
        param.put("startScore", startScore);
        param.put("endScore", endScore);


        List<Score> sightList = getScoreList(param);

        logger.info("获取的景点数量为" + sightList.size());

        List<String> users = new ArrayList<String>();

        for (int i = 0 ; i < sightList.size(); i++) {

            String sightName = sightList.get(i).getSightName();

            //获取该景点的所有记录
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sightName", sightName);
            map.put("startScore", startScore);
            map.put("endScore", endScore);

            List<Score> tempList = getScoreList(map);
            logger.info(sightName + "获取的用户数量为" + tempList.size());

            for(int j = 0 ; j < tempList.size() ; j++) {

                if (tempList.get(j).getUserName().equals(userName) || users.contains(tempList.get(j).getUserName())) {
                    logger.info("已经含有"+ tempList.get(j).getUserName() + "用户");
                } else {
                    logger.info("添加"+ tempList.get(j).getUserName() + "用户成功");
                    users.add(tempList.get(j).getUserName());
                }
            }
        }
        return users;
    }
}
