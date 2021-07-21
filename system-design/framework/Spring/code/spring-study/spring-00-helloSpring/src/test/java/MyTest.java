import com.xinx.service.UserInfoService;
import com.xinx.service.impl.UserInfoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/5/25 10:55
 */
public class MyTest {

    public static  void  main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserInfoServiceImpl userInfoService = context.getBean("userInfoServiceImpl", UserInfoServiceImpl.class);

        String result = userInfoService.getUserInfo();
        System.out.println(result);

    }

}
