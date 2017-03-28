package com.yin.trip.admin;

import com.yin.trip.admin.entity.Click;
import com.yin.trip.admin.service.ClickService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
public class ClickTest extends BaseTest{

    @Autowired
    private ClickService clickService;

    @Test
    public void insertClick(){
        //初始化数据
        Click click = new Click();

        click.setUserName("yin");
        click.setUserType("student");
        click.setSightName("世界之窗");
        click.setSightType("主题公园");
        click.setTime(new Date());

        clickService.insertClick(click);
    }
}
