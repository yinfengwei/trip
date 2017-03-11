package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.UserDao;
import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * Created by yinfeng on 2017/3/10 0010.
 *  账户操作类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 新建账户
     *
     * @param user
     * @return
     */
    @Override
    public boolean insertUser(User user) {

        //数据库中已经存在则不能插入
        if (userDao.getUserByName(user.getUserName()) != null) {
            return false;
        }

        //新建用户
        userDao.insertUser(user);
        //判断插入数据是否成功
        User tempUser = userDao.getUserByName(user.getUserName());

        //若为空则插入失败
        if (tempUser == null) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 判断用户名与密码是否正确
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean checkUserPassword(String userName, String password) {

        User user = userDao.getUserByName(userName);

        //用户不存在或者密码不正确
        if (user == null || (!password.equals(user.getPassword()))) {
            return false;

        } else {
            return true;
        }
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param name
     * @return
     */
    @Override
    public User getUserByUseName(String name) {
        return userDao.getUserByName(name);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {

        User user1 = getUserByUseName(user.getUserName());

        //判断手机号码与密保答案是否与数据库一致
        if (user1 == null || user1.getPhone() != user.getPhone()
                || (!user.getAnswer().equals(user1.getAnswer()))) {
            return false;
        } else {
            userDao.upodateUser(user);
            return true;
        }

    }
}
