package com.xinx.pattern;

import com.xinx.service.UserService;
import com.xinx.service.impl.UserServiceImpl;

/**
 * @description: 用户代理类
 * @author: JXIN
 * @time: 2021/6/20 22:37
 */
public class UserProxy implements UserService {

    // 目标对象
    private UserService userService;

    // 有参构造，传递目标对象
    public UserProxy(UserService service) {
        this.userService = service;
    }

    public void add() {

        if (userService != null) {
            // 执行一些其他操作
            this.beforeAdd();
            userService.add();
        }

    }

    private void beforeAdd() {
        System.out.println("=== 准备新增用户 ===");
    }

}
