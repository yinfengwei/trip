package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by yinfeng on 2017/4/29 0029.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springContext.xml"})
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private User user;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        user = new User();
        user.setUserName("test");
        user.setPassword("123456");
        user.setPhone("13528722483");
        user.setType("student");
        user.setAge(1);
        user.setSex(0);
    }

    @Test
    public void insertUser() throws Exception {
        assert true == userService.insertUser(user).isResult();
    }

    @Test
    public void checkUserPassword() throws Exception {
        String userName = "test";
        String password = "123456";

        userService.insertUser(user);

        assert userService.checkUserPassword(userName, password);

    }

    @Test
    public void getUserByUseName() throws Exception {
        //插入数据
        userService.insertUser(user);

        String name = "test";

        //判断获取是否成功
        User tempUser = userService.getUserByUseName(name);

        assert tempUser.getPassword().equals("123456");
    }

    @Test
    public void getUserByPhone() throws Exception {
        //插入数据
        userService.insertUser(user);

        String phone = "13528722483";

        //判断获取是否成功
        User tempUser = userService.getUserByPhone(phone);

        assert tempUser.getPassword().equals("123456");
    }

    @Test
    public void getUserList() throws Exception {
        logger.info(userService.getUserList(null).size() + "");
    }

    @Test
    public void getUserListByAge() throws Exception {
        userService.insertUser(user);

        Map<String, Integer> similarUser = new HashMap<String, Integer>();

        similarUser.put("test",0);

        Map<String, List<String>> result = userService.getUserListByAge(similarUser);
        logger.info("result : "  +  result.size());

    }

    @Test
    public void getUserListByType() throws Exception {
        userService.insertUser(user);

        Map<String, Integer> similarUser = new HashMap<String, Integer>();

        similarUser.put("test",0);

        Map<String, List<String>> result = userService.getUserListByType(similarUser);
        logger.info("result : "  +  result.size());

    }

    @Test
    public void getUserListBySex() throws Exception {
        //插入用户
        userService.insertUser(user);

        Map<String, Integer> similarUser = new HashMap<String, Integer>();

        similarUser.put("test",0);

        Map<String, List<String>> result = userService.getUserListBySex(similarUser);
        logger.info("result : "  +  result.size());

    }

    @Test
    public void getCorrelationUser() throws Exception {
        String userName = "test";

        userService.insertUser(user);

        List<String> users = userService.getCorrelationUser(userName);

//        Map<String, Object> param = new HashMap<String, Object>();


//        param.put("type", userService.getUserByUseName(userName).getType());

        for(String user : users) {

            logger.info(user);
        }
    }

    @Test
    public void updateUser() throws Exception {
        userService.insertUser(user);

        //更新
        user.setPassword("123");

        userService.updateUser(user);

        //判断
        User tempUser = userService.getUserByUseName("test");

        assert tempUser.getPassword().equals("123");
    }

}