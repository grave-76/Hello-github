package com.xinx.factory;

import com.xinx.common.Consts;

/**
 * @description: 工厂处理器
 * @author: JXIN
 */
public class FactoryHandler {

    /**
     * 获取工厂实例
     * @param choice
     * @return
     */
    public static AbstractFactory getFactory(String choice) {
        AbstractFactory factory = null;

        if (Consts.COLOR.equals(choice)) {
            factory = new ColorFactory();
        } else if (Consts.SHAPE.equals(choice)) {
            factory = new ShapeFactory();
        }

        return factory;
    }

}
