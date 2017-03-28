package com.yin.trip.admin.dao;

import com.yin.trip.admin.entity.Score;

import java.util.Map;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public interface ScoreDao {

    /**
     *  增加评分数据
     * @param score
     */
    void insertScore(Score score);

    /**
     *  根据条件获取评分记录
     * @param param
     * @return
     */
    Score getScore(Map<String, Object> param);

    /**
     *  更新评分记录
     * @param score
     */
    void updateScore(Score score);
}
