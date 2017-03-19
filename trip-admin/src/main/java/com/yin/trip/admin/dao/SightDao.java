package com.yin.trip.admin.dao;

import com.yin.trip.admin.entity.Sight;

import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/18 0018.
 */
public interface SightDao {

    /**
     *  插入数据
     * @param sight
     */
    void InsertData(Sight sight);

    /**
     *  获取景点列表
     *  @param map
     */
    List<Sight> getSights(Map<String, Object> map);

    /**
     *  查询获取特定景点信息
     *  @param map
     *  @return
     */
    Sight getSightByParam(Map<String,Object> map);

    /**
     *  更新景点数据
     *  @param sight
     */
    void update(Sight sight);

    /**
     *  景点总数
     * @return
     */
    int count();



}
