package com.xinx.aop;

/**
 * @description: 自定义切入点
 * @author: JXIN
 * @time: 2021/6/20 17:14
 */
public class MyPointcut {

    public void MyBefore() {
        System.out.println("准备执行方法...");
    }

    public void MyAfter() {
        System.out.println("已执行方法...");
    }

}
