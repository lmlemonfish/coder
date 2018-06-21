package db;

import constants.DbEnum;
import db.service.CommonDBService;
import engine.CoderEngine;
import entity.TableInfo;
import org.junit.Test;

/**
 * @ClassName db.DBTest
 * @Description TODO
 * @Author LM
 * @Date 2018/6/7 0007 下午 8:54
 * @Version 1.0
 **/
public class DBTest {

    private CoderEngine engine = new CoderEngine();

    @Test
    public void testConnection() {
        CommonDBService service = engine.getCommonDBService("Mysql");
        boolean isCon = service.checkConnection("localhost", "music", "3306", "root", "root");
        assert(isCon);
        System.out.println(engine.getConInfo());
    }

    @Test
    public void testConnectionForOracle() {
        CommonDBService service = engine.getCommonDBService("Oracle");
        boolean isCon = service.checkConnection("192.168.147.195", "ORCL", "1521", "stategrid", "stategrid");
        assert(isCon);
        System.out.println(engine.getConInfo());
    }

    @Test
    public void getTableInfo() {
        //测试连接
        testConnection();

        CommonDBService commonDBService = engine.getCommonDBService(DbEnum.MYSQL.getDbName());
        TableInfo info = commonDBService.getTableInfoFromDB("tb_content");

        System.out.println(info);
    }
}
