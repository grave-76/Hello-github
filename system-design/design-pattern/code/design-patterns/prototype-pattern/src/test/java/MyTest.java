import com.xinx.demo.User;

/**
 * @description:
 * @author: JXIN
 */
public class MyTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        User user = new User();
        User user2 = (User) user.clone();

        boolean result = user == user2;
        System.out.println("======对象是否相同：" + result);
    }

}
