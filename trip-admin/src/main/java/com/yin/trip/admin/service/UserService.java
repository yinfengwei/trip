package com.yin.trip.admin.service;

import com.yin.trip.admin.entity.User;

import java.util.List;
import java.util.Map;

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
     *  根据参数获取用户信息
     * @param param
     * @return
     */
    List<User> getUserList(Map<String, Object> param);

    /**
     *  根据用户名获取与该用户年龄、性别、类型任一方面相关的用户类表
     * @param name
     * @return
     */
    List<String> getCorrelationUser(String name);

    /**
     *  更新用户信息
     * @param user
     * @return
     */
    boolean updateUser(User user);
}
