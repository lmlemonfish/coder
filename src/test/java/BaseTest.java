import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
    @Before
    public void beforeTest() {
        //用于子类 测试之前进行操作
    }

    @After
    public void afterTest() {

    }
}
