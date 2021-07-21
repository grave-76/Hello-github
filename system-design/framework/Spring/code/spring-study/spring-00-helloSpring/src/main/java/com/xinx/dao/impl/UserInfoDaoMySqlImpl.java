package com.xinx.dao.impl;

import com.xinx.dao.UserInfoDao;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/11 23:13
 */
public class UserInfoDaoMySqlImpl implements UserInfoDao {

    @Override
    public String getInfo() {

        return "mysql实现";

    }
}
