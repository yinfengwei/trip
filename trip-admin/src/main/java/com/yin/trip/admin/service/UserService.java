package com.yin.trip.admin.service;

import com.yin.trip.admin.entity.User;

/**
 * Created by yinfeng on 2017/3/10 0010.
 */
public interface UserService {

    /**
     *  新建账户
     * @param user
     * @return
     */
    boolean insertUser(User user);

    /**
     *  判断用户名与密码是否正确
     * @param userName
     * @param password
     * @return
     */
    boolean checkUserPassword(String userName, String password);

    /**
     *  根据用户名获取用户信息
     * @param name
     * @return
     */
    User getUserByUseName(String name);

    /**
     *  更新用户信息
     * @param user
     * @return
     */
    boolean updateUser(User user);
}
