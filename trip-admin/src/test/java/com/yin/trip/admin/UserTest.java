package com.yin.trip.admin;

import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import com.yin.trip.common.util.PhoneFormatCheckUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/10 0010.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springContext.xml"})
@Transactional
public class UserTest {

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void init(){
        user = new User();
        user.setUserName("test");
        user.setPassword("123456");
        user.setPhone("13728722483");
        user.setType("student");
        user.setAge(1);
        user.setSex(0);
    }

    @Test
    public void testInsertUser() {
        System.out.println(userService.getUserByPhone(user.getPhone()));
        assert true == userService.insertUser(user).isResult();

    }

    @Test
    public void testCheckPassword(){
        String userName = "test";
        String password = "123456";

        userService.insertUser(user);

        assert userService.checkUserPassword(userName, password);

    }

    @Test
    public void testGetCorrelationUser(){

        String userName = "test";

        List<String> users = userService.getCorrelationUser(userName);

//        Map<String, Object> param = new HashMap<String, Object>();


//        param.put("type", userService.getUserByUseName(userName).getType());

        for(String user : users) {

            System.out.println(user);
        }
    }

    @Test
    public void testGetUserListByAge(){

        Map<String, Integer> similarUser = new HashMap<String, Integer>();

        similarUser.put("yin",0);

        Map<String, List<String>> result = userService.getUserListByAge(similarUser);
//        logger.info("result : "  +  result.size());

        System.out.println(result.get("yin"));
    }
    @Test
    public void testGetUserListByType(){

        Map<String, Integer> similarUser = new HashMap<String, Integer>();

        similarUser.put("yin",0);

        Map<String, List<String>> result = userService.getUserListByType(similarUser);
//        logger.info("result : "  +  result.size());

        System.out.println(result.get("yin").size());
    }
    @Test
    public void testGetUserListBySex(){

        Map<String, Integer> similarUser = new HashMap<String, Integer>();

        similarUser.put("yin",0);

        Map<String, List<String>> result = userService.getUserListBySex(similarUser);
//        logger.info("result : "  +  result.size());

        System.out.println(result.get("yin"));
    }

    @Test
    public void testPhoneFormat(){

        String phone = "13534535271";


        assert PhoneFormatCheckUtil.isPhoneLegal(phone);
    }
}
