package com.yin.trip.admin;

import com.yin.trip.admin.entity.Click;
import com.yin.trip.admin.service.ClickService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Test
    public void testGetScore(){
        String userName = "mouse";

        List<String> list = clickService.getSimUserByName(userName);

        for (String user : list) {
            System.out.println(user);
        }

    }

    /**
     *  用户倒排表
     */
    @Test
    public void testGetScoreList(){

        List<String> userNames = clickService.getSimUserByName("mouse");

        Map<String, List<String>> result = clickService.getClickChart("mouse",userNames);

//        System.out.print(result.size());

        Set<Map.Entry<String, List<String>>> entries = result.entrySet();

        for (Map.Entry<String, List<String>> entry : entries) {
            System.out.println(entry.getKey()+":"+
                    entry.getValue());

        }
    }
}
