package entity;

import java.util.Map;

/**
 * @ClassName TableInfo
 * @Description TODO
 * @Author LM
 * @Date 2018/6/6 0006 下午 5:57
 * @Version 1.0
 **/
public class TableInfo {

    private Map<String,String> fieldMap;//表字段 -key:字段名 -value:字段类型
    private String tableName;//表名

    public TableInfo(Map<String, String> fieldMap, String tableName) {
        this.fieldMap = fieldMap;
        this.tableName = tableName;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
