package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.UserDao;
import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/10 0010.
 *  账户操作类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
            logger.warn("数据库中已经存在则不能插入");
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
     * 根据参数获取用户信息
     *
     * @param param
     * @return
     */
    @Override
    public List<User> getUserList(Map<String, Object> param) {
        return userDao.getUserList(param);
    }

    /**
     * 根据参数获取用户信息
     *
     * @param similarUser
     * @return
     */
    @Override
    public Map<String, List<String>> getUserListByAge(Map<String, Integer> similarUser) {
        Map<String, List<String>> result = new HashMap<String, List<String>>();

        //遍历key
        for(String key : similarUser.keySet()) {

            //获得用户名
            User user = getUserByUseName(key);
            List<String> userList = new ArrayList<String>();

            Map<String, Object> param = new HashMap<String, Object>();

            param.put("age" , user.getAge());

            for(User tempUser : getUserList(param)) {

                //不能包含该用户且必须在用户列表中
                if ((!key.equals(tempUser.getUserName()))
                        && similarUser.containsKey(tempUser.getUserName())){
                    userList.add(tempUser.getUserName());
                }
            }

            result.put(key, userList);

        }

        return result;
    }



    /**
     * 获取相关用户类型相同的用户列表
     *
     * @param similarUser
     * @return
     */
    @Override
    public Map<String, List<String>> getUserListByType(Map<String, Integer> similarUser) {
        Map<String, List<String>> result = new HashMap<String, List<String>>();

        //遍历key
        for(String key : similarUser.keySet()) {
            //获得用户名
            User user = getUserByUseName(key);
            List<String> userList = new ArrayList<String>();

            Map<String, Object> param = new HashMap<String, Object>();

            param.put("type" , user.getType());

            for(User tempUser : getUserList(param)) {
                //不能包含该用户
                if ((!key.equals(tempUser.getUserName()))
                        && similarUser.containsKey(tempUser.getUserName())){
                    userList.add(tempUser.getUserName());
                }
            }

            result.put(key, userList);

        }
        return result;
    }

    /**
     * 获取相关用户性别相同的用户列表
     *
     * @param similarUser
     * @return
     */
    @Override
    public Map<String, List<String>> getUserListBySex(Map<String, Integer> similarUser) {
        Map<String, List<String>> result = new HashMap<String, List<String>>();

        //遍历key
        for(String key : similarUser.keySet()) {
            //获得用户名
            User user = getUserByUseName(key);
            List<String> userList = new ArrayList<String>();

            Map<String, Object> param = new HashMap<String, Object>();

            param.put("sex" , user.getSex());

            for(User tempUser : getUserList(param)) {
                //不能包含该用户
                if ((!key.equals(tempUser.getUserName()))
                        && similarUser.containsKey(tempUser.getUserName())){
                    userList.add(tempUser.getUserName());
                }
            }

            result.put(key, userList);

        }
        return result;
    }

    /**
     * 根据用户名获取与该用户年龄、性别、类型任一方面相关的用户类表
     *
     * @param name
     * @return
     */
    @Override
    public List<String> getCorrelationUser(String name) {

        User user = getUserByUseName(name);
        String type = user.getType();
        int age = user.getAge();
        int sex = user.getSex();

        //获取类型相同的用户
        List<String> result = new ArrayList<String>();
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("type", type);

        for (User tempUser : getUserList(param)) {

            //不包含该用户
            if (!tempUser.getUserName().equals(name)) {
                result.add(tempUser.getUserName());
            }

        }

        logger.info("获取到类型相同相关用户总数为" + result.size());

        //获取年龄段相同的用户
        param.remove("type");
        param.put("age", age);

        for (User tempUser : getUserList(param)) {

            //如果用户列表中不含有该用户则进行添加
            if ((!tempUser.getUserName().equals(name)) &&
                    (!result.contains(tempUser.getUserName()))){
                result.add(tempUser.getUserName());
            }
        }
        logger.info("获取到类型与年龄相同相关用户总数为" + result.size());

        //获取年龄段相同的用户
        param.remove("age");
        param.put("sex", sex);

        for (User tempUser : getUserList(param)) {

            //如果用户列表中不含有该用户则进行添加
            if ((!tempUser.getUserName().equals(name))
                    && (!result.contains(tempUser.getUserName()))){
                result.add(tempUser.getUserName());
            }
        }

        logger.info("获取到类型与年龄和性别相关用户总数为" + result.size());



        return result;
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

        //判断手机号码与数据库是否一致
        if (user1 == null || user1.getPhone() != user.getPhone()) {
            return false;
        } else {
            userDao.updateUser(user);
            return true;
        }

    }
}
