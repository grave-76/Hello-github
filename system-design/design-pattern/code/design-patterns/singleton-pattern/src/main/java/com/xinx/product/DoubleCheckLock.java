package com.xinx.product;

/**
 * @description: 双重校验锁
 * @author: JXIN
 * @time: 2021/5/25 22:27
 * 线程安全 性能高
 */
public class DoubleCheckLock {

    /** 私有化构造函数 */
    private DoubleCheckLock() {

    }

    // volatile 不稳定的，表示修饰的变量的值可能随时被别的线程修改
    // 使用volatile修饰的变量会强制将修改的值立即写入主存，主存中值的更新会使缓存中的值失效
    private static volatile DoubleCheckLock instance;

    /**
     * 获取实例
     * @return
     */
    public static DoubleCheckLock getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLock.class) {
                if (instance == null) {
                    instance = new DoubleCheckLock();
                }
            }
        }
        return instance;
    }

}
