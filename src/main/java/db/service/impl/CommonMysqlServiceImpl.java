package db.service.impl;

import com.google.common.base.Strings;
import constants.DbEnum;
import db.service.CommonDBService;
import engine.CoderEngine;
import entity.ConInfo;
import entity.TableInfo;
import exception.BusinessException;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @ClassName CommonMysqlServiceImpl
 * @Description TODO
 * @Author LM
 * @Date 2018/6/6 0006 下午 5:43
 * @Version 1.0
 **/
public class CommonMysqlServiceImpl implements CommonDBService {

    private final String MYSQL_DRIVER = DbEnum.MYSQL.getDriveName();

    @Override
    public TableInfo getTableInfoFromDB(String tableName) {
        CoderEngine engine = new CoderEngine();
        //获取ConInfo对象,判断是否连接成功
        ConInfo conInfo = engine.getConInfo();
        //若数据库未连接成功,抛出异常
        if(!conInfo.isConSuccess()) {
            throw new BusinessException("数据库未连接成功!");
        }
        if(Strings.isNullOrEmpty(tableName)) {
            throw new BusinessException("表名为空!","tableName属性");
        }
        //读表
        Connection connection = JdbcUtils.getConnection(MYSQL_DRIVER,
                conInfo.getUrl(),
                conInfo.getUserName(),
                conInfo.getPassword());

        ResultSet columns = null;

        DatabaseMetaData metaData = null;
        TableInfo tableInfo = new TableInfo();
        HashMap<String, TableInfo.FieldMeTa> map = new HashMap<>();

        try {
            metaData = connection.getMetaData();
            columns = metaData.getColumns(null, "%", tableName, "%");
            while(columns.next()) {
                //字段名
                String columnName = columns.getString("COLUMN_NAME");
                //字段类型
                String typeName = columns.getString("TYPE_NAME");
                //字段大小
                int columnSize = columns.getInt("COLUMN_SIZE");
                //是否可为null
                int nullable = columns.getInt("NULLABLE");
                //注释
                String note = columns.getString("REMARKS");

                //封装到对象
                TableInfo.FieldMeTa fieldMeTa = tableInfo.new FieldMeTa(typeName,columnSize,note,nullable);
                if(columnName != null) {
                    map.put(columnName,fieldMeTa);
                }
            }

            tableInfo.setTableName(tableName);
            tableInfo.setFieldMap(map);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeResource(connection,null,columns);
        }

        return tableInfo;
    }

    @Override
    public boolean checkConnection(String url, String userName, String password) {

        Connection connection = JdbcUtils.getConnection(MYSQL_DRIVER, url, userName, password);
        if(connection != null) {
            //封装到ConInfo对象
            ConInfo conInfo = ConInfo.getInstance(MYSQL_DRIVER, url, userName, password);
            //关闭连接
            JdbcUtils.closeConnection(connection);
            return conInfo.isConSuccess();
        }

        return false;
    }
}
