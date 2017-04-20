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
     *   根据类型获取景点列表中的某一部分
     *  @param offset 从0 开始
     *  @param length
     *  @param sightType
     */
    List<Sight> getSights(int offset, int length, String sightType);


    /**
     *   获取景点列表
     *
     */
    List<Sight> getSights(String sightType);



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
     *  获取景点类型总数
     * @param sightType
     * @return
     */
    int getCountBySightType(String sightType);

    /**
     *  推荐景点
     */
    List<Map.Entry<Sight, Double>> getRecommend(String userName, Map<String, Double> locationScore ,String sightType);

     Map<String,Double> getTopSimilar (Map<String,Double> similar , int num);

    Map<String, Double> getSimilar(String userName);

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


    /**
     *  判断景点是否属于景点类型
     * @param name
     * @param sightType
     * @return
     */
    boolean belongSightType(String name, String sightType);
}
