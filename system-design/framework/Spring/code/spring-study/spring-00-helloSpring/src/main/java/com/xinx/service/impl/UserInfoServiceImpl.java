package com.xinx.service.impl;

import com.xinx.dao.UserInfoDao;
import com.xinx.service.UserInfoService;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/11 23:12
 */
public class UserInfoServiceImpl implements UserInfoService {

    // 1、自己创建对象
    //UserInfoDao UserInfoDao = new UserInfoDaoImpl();

    // 2、Spring创建
    UserInfoDao userInfoDao;
    // 利用set方法实现动态注入
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public String getUserInfo() {

        return userInfoDao.getInfo();

    }
}
