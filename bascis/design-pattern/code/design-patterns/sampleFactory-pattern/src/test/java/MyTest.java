import com.xinx.common.Consts;
import com.xinx.product.Shape;
import com.xinx.factory.ShapeFactory;

/**
 * @description:
 * @author: JXIN
 */
public class MyTest {

    public static void main(String[] args) {

        // 实例化形状工厂
        ShapeFactory shapeFactory = new ShapeFactory();
        // 获取指定形状
        Shape shape = shapeFactory.getShape(Consts.SHAPE_CIRCLE);
        // 调用方法
        shape.draw();

    }

}
