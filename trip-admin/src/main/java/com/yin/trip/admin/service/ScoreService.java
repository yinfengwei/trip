package com.yin.trip.admin.service;

import com.yin.trip.admin.entity.Score;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public interface ScoreService {

    /**
     *  增加评分记录
     * @param score
     */
    void insertScore(Score score);
}
