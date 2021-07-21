package com.xinx.product;

/**
 * @description: 懒汉式
 * @author: JXIN
 * @time: 2021/5/25 21:54
 * 线程不安全
 */
public class LazyPattern {

    /**
     * 单例模式三个主要特点：
     * 1、构造方法私有化；
     * 2、实例化的变量引用私有化；
     * 3、获取实例的方法共有。
     */

    /** 私有化构造函数 */
    private LazyPattern() {

    }

    private static LazyPattern instance;

    /**
     * 获取实例
     * @return
     */
    public static LazyPattern getInstance() {
        if (instance == null)
        {
            instance = new LazyPattern();
        }
        return instance;
    }

}
