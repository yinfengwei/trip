package com.yin.trip.admin.service;

import com.yin.trip.admin.entity.Distance;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.common.entity.BaiDuLocation;

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

//    /**
//     *   获取景点列表
//     *
//     */
//    List<Sight> getSightsById(int id);

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
     *  更新景点数据
     *
     */
    void updateAll(Sight sight);

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
    List<Map.Entry<Sight, Double>> getRecommend(String userName, Map<String, Double> locationScore);

    Map<String, Object> getSimilar(String userName);

    /**
     *  获取景点与当前位置的距离并排序
     * @param location
     * @return
     */
    List<Distance> getDistance(BaiDuLocation location);


    /**
     *  根据距离信息对景点进行距离得分比例计算
     * @param distances
     * @return
     */
    Map<String,Double> getDistanceScore(List<Distance> distances);



}
