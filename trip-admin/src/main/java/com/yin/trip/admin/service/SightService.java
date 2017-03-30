package com.yin.trip.admin.service;

import com.yin.trip.admin.entity.Sight;

import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/18 0018.
 */
public interface SightService {

    /**
     *  插入景点信息
     * @param sight
     */
    void insertData(Sight sight);

    /**
     *   获取景点列表
     *
     */
    List<Sight> getSights(int start, int end);

    /**
     *   获取景点列表
     *
     */
    List<Sight> getSights();

    /**
     *  根据景点名获取景点信息
     * @param name
     * @return
     */
    Sight getSightByName(String name);

    /**
     *  更新景点数据
     *
     */
    void update(String name, int rank);

    /**
     *  更新景点评分与评分人数
     *
     */
    void updateScore(String name, float userScore ,long userSum);

    /**
     *  获取景点总数
     * @return
     */
    int getCount();

    /**
     *  推荐景点
     */
    List<Map.Entry<Sight, Double>> getRecommend(String userName);

    Map<String, Object> getSimilar(String userName);

}
