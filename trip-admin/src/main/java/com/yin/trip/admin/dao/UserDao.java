package com.yin.trip.admin.dao;

import com.yin.trip.admin.entity.User;

import java.util.List;
import java.util.Map;

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
     *  根据手机号码查找用户
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     *  根据条件查找用户
     * @param param
     * @return
     */
    List<User> getUserList(Map<String,Object> param);

    /**
     *  更新用户信息
     * @param user
     */
    void updateUser(User user);


}
