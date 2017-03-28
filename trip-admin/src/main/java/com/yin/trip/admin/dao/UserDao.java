package com.yin.trip.admin.dao;

import com.yin.trip.admin.entity.User;

/**
 * Created by yinfeng on 2017/3/10 0010.
 */
public interface UserDao {

    /**
     *  新建账户
     * @param user
     * @return
     */
    void insertUser(User user);

    /**
     *  根据用户名查找用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     *  更新用户信息
     * @param user
     */
    void updateUser(User user);


}
