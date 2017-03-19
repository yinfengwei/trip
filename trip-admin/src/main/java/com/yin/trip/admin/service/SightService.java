package com.yin.trip.admin.service;

import com.yin.trip.admin.entity.Sight;

import java.util.List;

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
     *  获取景点总数
     * @return
     */
    int getCount();
}
