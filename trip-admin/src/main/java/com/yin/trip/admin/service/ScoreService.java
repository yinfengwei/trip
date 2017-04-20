package com.yin.trip.admin.service;

import com.yin.trip.admin.entity.Score;

import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public interface ScoreService {

    /**
     *  增加评分记录
     * @param score
     */
    void insertScore(Score score);

    /**
     *  根据用户列表获取
     *  获取评分用户倒查表
     *
     *
     */
    Map<String, List<String>> getScoreChart(String name,List<String> usersName);

    /**
     *  根据参数获取数据
     */
    List<Score> getScoreList(Map<String, Object> param);

    /**
     *  通过用户名获取与该用户去过同个景点的用户列表且满足评分限制，[startScore, endScore]
     * @param userName
     * @return
     */
    List<String> getSimUserByName(String userName, int startScore, int endScore);

}
