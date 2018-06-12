package constants;

import java.util.HashMap;

/**
 * @ClassName DicConstants
 * @Description TODO
 * @Author LM
 * @Date 2018/6/12 0012 上午 10:28
 * @Version 1.0
 **/
public class DicConstants {

    private static DicConstants dicConstants = null;

    private static Object object = new Object();

    private HashMap<String, String> map = new HashMap<>();

    private DicConstants() {
    }

    private void init() {
        map.put("BIGINT", "Long");
        map.put("INT", "Integer");
        map.put("SMALLINT", "Short");
        map.put("FLOAT", "Double");
        map.put("DOUBLE", "Double");
        map.put("VARCHAR", "String");
        map.put("CHAR", "String");
        map.put("LONGVARCHAR", "String");
        map.put("DECIMAL", "BigDecimal");
        map.put("TEXT", "String");
        map.put("BLOB", "byte[]");
        map.put("CLOB", "byte[]");
        map.put("BOOLEAN", "boolean");
        map.put("DATETIME", "Date");
    }

    public static DicConstants getInstance() {

        if (dicConstants == null) {
            synchronized (object) {
                if (dicConstants == null) {
                    DicConstants dicConstants = new DicConstants();
                    dicConstants.init();
                    return dicConstants;
                }
            }
        }
        return dicConstants;
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
