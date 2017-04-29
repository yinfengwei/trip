package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.entity.Click;
import com.yin.trip.admin.service.ClickService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by yinfeng on 2017/4/29 0029.
 */
public class ClickServiceImplTest extends BaseTest{
    @Autowired
    private ClickService clickService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void insertClick() throws Exception {
        //初始化数据
        Click click = new Click();

        click.setUserName("yin");
        click.setUserType("student");
        click.setSightName("世界之窗");
        click.setSightType("主题公园");
        click.setTime(new Date());

        clickService.insertClick(click);
    }

    @Test
    public void getClickChart() throws Exception {

        List<String> userNames = clickService.getSimUserByName("yin");
        Map<String, List<String>> result = clickService.getClickChart("yin",userNames);

        logger.info(result.size() + "");

    }

    @Test
    public void getSimUserByName() throws Exception {
        String userName = "yin";

        List<String> list = clickService.getSimUserByName(userName);

        for (String user : list) {
            logger.info(user);
        }
    }

    @Test
    public void getClickList() throws Exception {
        List<String> userNames = clickService.getSimUserByName("yin");

        Map<String, List<String>> result = clickService.getClickChart("yin",userNames);

//        System.out.print(result.size());

        Set<Map.Entry<String, List<String>>> entries = result.entrySet();

        for (Map.Entry<String, List<String>> entry : entries) {
           logger.info(entry.getKey()+":"+
                    entry.getValue());

        }
    }

}