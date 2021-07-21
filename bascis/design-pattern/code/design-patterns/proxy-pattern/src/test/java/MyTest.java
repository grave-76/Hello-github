import com.xinx.pattern.CglibProxyFactory;
import com.xinx.pattern.DynamicProxyHandler;
import com.xinx.service.UserService;
import com.xinx.service.impl.UserServiceImpl;
import com.xinx.usual.Manager;

/**
 * @description:
 * @author: JXIN
 * @time: 2021/6/20 22:42
 */
public class MyTest {

    public static void main(String[] args) {

        // ********** 静态代理 **********
        //UserService userService = new UserProxy(new UserServiceImpl());
        //userService.add();

        // ********** 动态代理 **********

        // 生成$Proxy0的class文件，在项目根目录的com/sun/proxy文件夹下
        // System.getProperties().put( "sun.misc.ProxyGenerator.saveGeneratedFiles" , "true" );

        // 创建动态代理处理对象
        //DynamicProxyHandler dynamicProxy = new DynamicProxyHandler(new UserServiceImpl());
        //// 获取目标代理对象，返回值是代理的接口，并非其实现类
        //UserService proxy = (UserService) dynamicProxy.getProxyInstance();
        //proxy.add();

        // ********** Cglib 代理 **********
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(new Manager());
        Manager manager = (Manager) cglibProxyFactory.getProxyInstance();
        manager.addAccount();
    }

}
