import db.service.CommonDBService;
import engine.CoderEngine;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName DBTest
 * @Description TODO
 * @Author LM
 * @Date 2018/6/7 0007 下午 8:54
 * @Version 1.0
 **/
public class DBTest extends BaseTest {

    @Before
    @Override
    public void beforeTest() {

    }

    @Test
    public void testConnection() {
        CoderEngine engine = new CoderEngine();
        CommonDBService service = engine.getCommonDBService("Mysql");
        boolean isCon = service.checkConnection("jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true", "root", "root");
        assert(isCon);
        System.out.println(engine.getConInfo());

    }
}
