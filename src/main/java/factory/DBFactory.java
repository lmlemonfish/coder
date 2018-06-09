package factory;

import com.google.common.base.Strings;
import db.service.CommonDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName DBFactory
 * @Description TODO 工厂类:兼容不同数据库
 * @Author LM
 * @Date 2018/6/6 0006 下午 5:38
 * @Version 1.0
 **/
public class DBFactory {

    static Logger logger = LoggerFactory.getLogger(DBFactory.class);
    //包名前缀
    private static final String DB_PRE_PACK = "db.service.impl.";
    //服务后缀
    private static final String DB_POST_PACK = "ServiceImpl";
    //数据库名称
    private static String db = null;

    public static CommonDBService getCommonDBService() {

        if(checkParam()) {
            logger.debug("[db] -> 参数为空");
            //TODO 之后改为抛出异常
            return null;
        }

        //反射生成对象
        //多态
        CommonDBService commonDBService = null;
        //拼接模块名称
        String modelName = "Common";

        String className = DB_PRE_PACK + modelName + db + DB_POST_PACK;

        try {
            Class<?> name = Class.forName(className);
            commonDBService = (CommonDBService)name.newInstance();

        } catch (Exception e) {
            logger.debug("创建实例类->" + className + "失败!");
            e.printStackTrace();
        }

        return commonDBService;
    }

    public static void setDb(String db) {
        DBFactory.db = db;
    }

    private static boolean checkParam() {
        if(Strings.isNullOrEmpty(db)) return true;
        return false;
    }

}
