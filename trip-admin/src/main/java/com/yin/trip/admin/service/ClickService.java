package com.yin.trip.admin.service;

import com.yin.trip.admin.entity.Click;

import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public interface ClickService {

    /**
     *  增加点击记录
     * @param click
     */
    void insertClick(Click click);

    /**
     *  根据用户列表获取
     *  获取评分用户倒查表
     *
     *
     */
    Map<String, List<String>> getClickChart(String name,List<String> usersName);

    /**
     *  通过用户名获取与该用户去过同个景点的用户列表
     * @param userName
     * @return
     */
    List<String> getSimUserByName(String userName);


    /**
     *  根据参数获取数据
     */
    List<Click> getClickList(Map<String, Object> param);
}
