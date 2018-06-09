package db.service;

import entity.TableInfo;

/**
 * @ClassName CommonDBService
 * @Description TODO
 * @Author LM
 * @Date 2018/6/6 0006 下午 5:53
 * @Version 1.0
 **/
public interface CommonDBService {

    /**
     * 从数据库中获得表信息
     * @return
     */
    TableInfo getTableInfoFromDB(String tableName);

    /**
     * 检查是否连接成功数据库
     * @param url
     * @param userName
     * @param password
     * @return
     */
    boolean checkConnection(String url, String userName, String password);


}
