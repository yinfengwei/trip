package com.yin.trip.admin.dao;

import com.yin.trip.admin.entity.Click;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public interface ClickDao {

    /**
     *  增加点击记录
     * @param click
     */
    void insertClick(Click click);
}
