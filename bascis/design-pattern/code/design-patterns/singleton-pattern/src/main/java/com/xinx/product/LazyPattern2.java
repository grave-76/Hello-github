package com.xinx.product;

/**
 * @description: 懒汉式 线程安全
 * @author: JXIN
 * @time: 2021/5/25 22:08
 * 加同步锁保证线程安全，但加锁会影响效率
 */
public class LazyPattern2 {

    /** 私有化构造函数 */
    private LazyPattern2() {

    }

    private static LazyPattern2 instance;

    /**
     * 获取实例-加锁
     * @return
     */
    public static synchronized LazyPattern2 getInstance() {
        if (instance == null) {
            instance = new LazyPattern2();
        }
        return instance;
    }

}
