package db.service.impl;

import constants.DbEnum;
import db.service.CommonDBService;
import entity.ConInfo;
import entity.TableInfo;
import utils.JdbcUtils;

import java.sql.Connection;

/**
 * @ClassName CommonOracleServiceImpl
 * @Description TODO 针对Oracle数据库的Db服务类
 * @Author LM
 * @Date 2018/6/16 0016 下午 3:17
 * @Version 1.0
 **/
public class CommonOracleServiceImpl implements CommonDBService {

    private final String driver = DbEnum.ORACLE.getDriveName();

    @Override
    public TableInfo getTableInfoFromDB(String tableName) {
        return null;
    }

    @Override
    public boolean checkConnection(String url, String userName, String password) {

        Connection connection = JdbcUtils.getConnection(driver, url, userName, password);
        if(connection != null) {
            //封装到info对象,单例,方便获取数据
            ConInfo conInfo = ConInfo.getInstance(driver, url, userName, password);
            //关闭连接
            JdbcUtils.closeConnection(connection);
            return conInfo.isConSuccess();
        }

        return false;
    }
}
