package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.ClickDao;
import com.yin.trip.admin.entity.Click;
import com.yin.trip.admin.service.ClickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/27 0027.
 */
@Service
public class ClickServiceImpl implements ClickService{

    @Autowired
    private ClickDao clickDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 增加点击记录
     *
     * @param click
     */
    @Override
    public void insertClick(Click click) {
        clickDao.insertClick(click);
    }

    /**
     * 根据用户列表获取
     * 获取评分用户倒查表
     *
     * @param name
     */
    @Override
    public Map<String, List<String>> getClickChart(String name, List<String> usersName) {

        //获取相关的用户列表
        Map<String, Object> param = new HashMap<String, Object>();

        //倒排表需要分析的用户数据
//        List<String> usersName = getSimUserByName(name);
        usersName.add(name);

        param.put("usersName", usersName);

        //倒查表，遍历所有点击数据。获得点击表
        List<Click> list = clickDao.getClickList(param);

        Map<String, List<String>> result = new HashMap<String, List<String>>();


        //添加第一个物品-用户数据

        //添加物品-用户数据
        for (int i = 0; i < list.size(); i++) {

            String sightName = list.get(i).getSightName();
            String userName = list.get(i).getUserName();

            List<String> tempUsers;
            //如果数据库中已经存在该景点，则添加list
            if (result.containsKey(sightName)) {
                tempUsers = result.get(sightName);

                //如果列表中已经包含则不操作
                if (!tempUsers.contains(userName)) {
                    tempUsers.add(userName);
                }

            } else {

                //创建新的用户表
                tempUsers = new ArrayList<String>();

                tempUsers.add(userName);
            }

            result.put(sightName, tempUsers);
        }


        return result;
    }

    /**
     * 通过用户名获取与该用户去过同个景点的用户列表
     *
     * @param userName
     * @return
     */
    @Override
    public List<String> getSimUserByName(String userName) {

        //获取该用户去过的景点列表
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userName", userName);

        List<Click> clickList = getClickList(param);

        logger.info("获取点击的景点数量为" + clickList.size());

        List<String> users = new ArrayList<String>();

        for (int i = 0 ; i < clickList.size(); i++) {

            String sightName = clickList.get(i).getSightName();

            //获取该景点的所有记录
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sightName", sightName);

            List<Click> tempList = getClickList(map);

            logger.info(sightName + "获取的用户数量为" + tempList.size());

            for(int j = 0 ; j < tempList.size() ; j++) {

                if (tempList.get(j).getUserName().equals(userName) || users.contains(tempList.get(j).getUserName())) {
                    logger.info("已经含有"+ tempList.get(j).getUserName() + "用户");

                } else {

                    logger.info("添加"+ tempList.get(j).getUserName() + "用户成功");
                    users.add(tempList.get(j).getUserName());
                }
            }
        }
        return users;
    }

    /**
     * 根据参数获取数据
     *
     * @param param
     */
    @Override
    public List<Click> getClickList(Map<String, Object> param) {
        return clickDao.getClickList(param);
    }
}
