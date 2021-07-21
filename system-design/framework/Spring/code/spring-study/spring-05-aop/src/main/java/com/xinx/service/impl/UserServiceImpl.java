package com.xinx.service.impl;

import com.xinx.model.UserInfo;
import com.xinx.service.UserService;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/6/20 16:08
 */
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("****** 增加一个用户 ******");
    }

    @Override
    public void sub() {
        System.out.println("****** 减少一个用户 ******");
    }
}
