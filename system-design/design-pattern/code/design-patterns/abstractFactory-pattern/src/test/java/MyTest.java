import com.xinx.factory.FactoryHandler;
import com.xinx.common.Consts;
import com.xinx.factory.AbstractFactory;
import com.xinx.product.color.Color;
import com.xinx.product.shape.Shape;

/**
 * @description:
 * @author: JXIN
 */
public class MyTest {

    public static void main(String[] args) {

        // 获取形状工厂
        AbstractFactory shapeFactory = FactoryHandler.getFactory(Consts.SHAPE);
        Shape shape = shapeFactory.getShape(Consts.SHAPE_SQUARE);
        shape.draw();

        // 获取颜色工厂
        AbstractFactory colorFactory = FactoryHandler.getFactory(Consts.COLOR);
        Color color = colorFactory.getColor(Consts.COLOR_RED);
        color.filling();

    }

}
