package com.xinx.product;

/**
 * @description: 饿汉式
 * @author: JXIN
 * @time: 2021/5/25 22:02
 * 线程安全，且未加锁，效率高
 * 但类加载时就初始化，浪费内存
 */
public class HungryPattern {

    /** 私有化构造函数 */
    private HungryPattern() {

    }

    private static HungryPattern instance = new HungryPattern();

    /**
     * 获取实例
     * @return
     */
    public static HungryPattern getInstance() {
        return instance;
    }

}
