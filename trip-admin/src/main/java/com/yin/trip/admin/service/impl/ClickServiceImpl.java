package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.ClickDao;
import com.yin.trip.admin.entity.Click;
import com.yin.trip.admin.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
@Service
public class ClickServiceImpl implements ClickService{

    @Autowired
    private ClickDao clickDao;

    /**
     * 增加点击记录
     *
     * @param click
     */
    @Override
    public void insertClick(Click click) {
        clickDao.insertClick(click);
    }
}
