package com.xinx.factory;

import com.xinx.common.Consts;
import com.xinx.product.Circle;
import com.xinx.product.Shape;
import com.xinx.product.Square;

/**
 * @description: 形状工厂
 * @author: JXIN
 */
public class ShapeFactory {

    /**
     * 获取形状实例
     * @param shapeType
     * @return
     */
    public Shape getShape(String shapeType) {
        Shape shape = null;
        if (Consts.SHAPE_CIRCLE.equals(shapeType)) {
            shape = new Circle();
        } else if (Consts.SHAPE_SQUARE.equals(shapeType)) {
            shape = new Square();
        }

        return shape;
    }

}
