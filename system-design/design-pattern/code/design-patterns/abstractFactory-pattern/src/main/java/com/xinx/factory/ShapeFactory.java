package com.xinx.factory;

import com.xinx.common.Consts;
import com.xinx.factory.AbstractFactory;
import com.xinx.product.color.Color;
import com.xinx.product.shape.Circle;
import com.xinx.product.shape.Shape;
import com.xinx.product.shape.Square;

/**
 * @description: 形状工厂
 * @author: JXIN
 */
public class ShapeFactory extends AbstractFactory {

    /**
     * 获取形状实例
     * @param shapeType
     * @return
     */
    @Override
    public Shape getShape(String shapeType) {
        Shape shape = null;
        if (Consts.SHAPE_CIRCLE.equals(shapeType)) {
            shape = new Circle();
        } else if (Consts.SHAPE_SQUARE.equals(shapeType)) {
            shape = new Square();
        }

        return shape;
    }

    @Override
    public Color getColor(String colorType) {
        return null;
    }

}
