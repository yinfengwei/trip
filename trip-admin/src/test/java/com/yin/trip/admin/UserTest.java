package com.yin.trip.admin;

import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
        user.setPhone(123456);
        user.setType("student");
        user.setAge(1);
        user.setSex(0);
    }

    @Test
    public void testInsertUser() {
        assert true == userService.insertUser(user);

    }

    @Test
    public void testCheckPassword(){
        String userName = "test";
        String password = "123456";

        userService.insertUser(user);

        assert userService.checkUserPassword(userName, password);

    }

    @Test
    public void test(){

    }
}
