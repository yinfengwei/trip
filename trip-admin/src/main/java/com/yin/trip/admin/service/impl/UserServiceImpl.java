package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.UserDao;
import com.yin.trip.admin.entity.Result;
import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.UserService;
import com.yin.trip.common.util.PhoneFormatCheckUtil;
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
    public Result insertUser(User user) {

        Result result = new Result();

        //数据库中已经存在则不能插入
        if (userDao.getUserByName(user.getUserName()) != null) {
            logger.warn("数据库中已经存在则不能插入");
            result.setResult(false);
            result.setMessage("数据库中已经存在则不能插入");
            return result;
        }

        //判断手机号码格式是否准确
        if(!PhoneFormatCheckUtil.isPhoneLegal(user.getPhone())){
            logger.warn("手机号码格式不正确");
            result.setResult(false);
            result.setMessage("手机号码格式不正确");
            return result;
        }

        //判断手机号码是否已经存在
        if(userDao.getUserByPhone(user.getPhone()) != null){

            logger.warn("手机号码已经注册过账号");
            result.setResult(false);
            result.setMessage("手机号码已经注册过账号");
            return result;
        }

        //新建用户
        userDao.insertUser(user);
        //判断插入数据是否成功
        User tempUser = userDao.getUserByName(user.getUserName());

        //若为空则插入失败
        if (tempUser == null) {
            logger.warn("数据库插入数据操作失败");
            result.setResult(false);
            result.setMessage("数据库插入数据操作失败");

            return result;
        } else {
            logger.warn("数据库插入数据操作成功");
            result.setResult(true);
            result.setMessage("数据库插入数据成功");

            return result;
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
     * 根据手机号码获取用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
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

                //该用户的相关用户必须在用户列表中且不能为该用户
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
    public Result updateUser(User user) {

        Result result = new Result();

        //根据用户名获取用户
        User originalUser  = getUserByUseName(user.getUserName());


        //判断用户名是否存在
        if(originalUser == null) {
            result.setResult(false);
            result.setMessage("用户名不存在");
            logger.warn("用户名不存在");

        } else if (!originalUser.getPhone().equals(user.getPhone())) {
            result.setResult(false);
            result.setMessage("手机号码与用户名不匹配");
            logger.warn("手机号码与用户名不匹配");

        } else {

            result.setResult(true);
            logger.warn("更新密码成功");
        }

        return result;

    }
}
