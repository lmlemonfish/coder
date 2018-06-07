package utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @ClassName JdbcUtils
 * @Description TODO jdbc工具类
 * @Author LM
 * @Date 2018/6/6 0006 下午 5:04
 * @Version 1.0
 **/
public class JdbcUtils {

    Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    private static String driver;
    private static String url;
    private static String userName;
    private static String password;

    /*static {
        try {
            //加载驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 获取连接
     * @param driver
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static Connection getConnection(String driver, String url, String userName, String password) {

        try {
            //加载驱动,*考虑到可能存在需要切换驱动的需求
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
                //gc
                resultSet = null;
            }
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection) {
        closeResource(connection,null,null);
    }
    public static void closeStatement(Statement statement) {
        closeResource(null,statement,null);
    }
    public static void closeResultSet(ResultSet resultSet) {
        closeResource(null,null,resultSet);
    }
}
