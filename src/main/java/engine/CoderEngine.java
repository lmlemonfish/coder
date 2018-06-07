package engine;

import db.service.CommonDBService;
import entity.ConInfo;
import factory.DBFactory;

/**
 * @ClassName CoderEngine
 * @Description TODO 引擎类,通过引擎类拿到各个对象(V1.0之后加入 对获得的对象进行代理)
 * @Author LM
 * @Date 2018/6/7 0007 下午 8:06
 * @Version 1.0
 **/
public class CoderEngine {

    public ConInfo getConInfo() {
        return ConInfo.getInstance();
    }

    public CommonDBService getCommonDBService(String db) {
        DBFactory.setDb(db);
        return DBFactory.getCommonDBService();
    }


}
