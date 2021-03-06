package com.yin.trip.admin.dao;

import com.yin.trip.admin.entity.Score;

import java.util.List;
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
    List<Score> getScore(Map<String, Object> param);

    /**
     *  根据条件获取评论记录
     * @param param
     * @return
     */
    List<Score> getScoreWithComment(Map<String, Object> param);



    /**
     *  更新评分记录
     * @param score
     */
    void updateScore(Score score);

    /**
     *
     *  根据参数获取数据
     * @param map
     * @return
     */
    List<Score> getScoreList(Map<String, Object> map);
}
