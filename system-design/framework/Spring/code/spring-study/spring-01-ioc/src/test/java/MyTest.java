import com.xinx.pojo.UserInfo;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/11 22:12
 */
public class MyTest {

    public static void main(String[] args) {

        // 在配置文件加载时初始化spring容器，实例化所有bean
        // 默认单例模式

        // 获取Spring上下文对象（Spring当前运行环境）
        /**
         * new ClassPathXmlApplicationContext对象时，会调用有参构造器中的refresh()方法
         * refresh()会销毁原先的ApplicationContext，创新初始化
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 获取beans.xml中配置的对象，通过id
        
        // 1
        UserInfo userInfo = (UserInfo) context.getBean("userInfo");

        // 2、创建方式
        UserInfo userInfo1 = (UserInfo) context.getBean("byProperty");
        UserInfo userInfo2 = (UserInfo) context.getBean("byIndex");
        UserInfo userInfo3 = (UserInfo) context.getBean("byType");

        // 单例模式测试
        UserInfo userInfo4 = (UserInfo) context.getBean("byProperty");
        if (userInfo1 == userInfo4)
        {
            System.out.println("======singleton pattern");
        }

        // 别名测试
        UserInfo userInfo5 = (UserInfo) context.getBean("user3");
        System.out.println("======通过别名获取bean:" + userInfo5.toString());

        // import
        ApplicationContext context2 = new ClassPathXmlApplicationContext("config.xml");
        UserInfo userInfo6 = (UserInfo) context2.getBean("user");
        System.out.println("======import测试：" + userInfo6.toString());


    }

}
