import com.xinx.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/6/20 17:05
 */
public class MyTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 动态代理所代理的是接口，使用实现类将报错
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();

    }

}
