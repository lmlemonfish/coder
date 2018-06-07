package db.service.impl;

import db.service.CommonDBService;
import entity.ConInfo;
import entity.TableInfo;
import utils.JdbcUtils;

import java.sql.Connection;

/**
 * @ClassName CommonMysqlServiceImpl
 * @Description TODO
 * @Author LM
 * @Date 2018/6/6 0006 下午 5:43
 * @Version 1.0
 **/
public class CommonMysqlServiceImpl implements CommonDBService {

    private final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    @Override
    public TableInfo getTableInfoFromDB() {
        return null;
    }

    @Override
    public boolean checkConnection(String url, String userName, String password) {

        Connection connection = JdbcUtils.getConnection(MYSQL_DRIVER, url, userName, password);
        if(connection != null) {
            //封装到ConInfo对象
            ConInfo conInfo = ConInfo.getInstance(MYSQL_DRIVER, url, userName, password);
            return conInfo.isConSuccess();
        }

        return false;
    }
}
