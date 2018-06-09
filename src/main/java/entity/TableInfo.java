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

    //内部类,表示表字段的附加信息
    public class FieldMeTa{
        private String fieldType;
        private Integer filedSize;
        private String note;
        private Integer nullable;

        public FieldMeTa() {
        }

        public FieldMeTa(String fieldType, Integer filedSize, String note, Integer nullable) {
            this.fieldType = fieldType;
            this.filedSize = filedSize;
            this.note = note;
            this.nullable = nullable;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public Integer getFiledSize() {
            return filedSize;
        }

        public void setFiledSize(Integer filedSize) {
            this.filedSize = filedSize;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Integer getNullable() {
            return nullable;
        }

        public void setNullable(Integer nullable) {
            this.nullable = nullable;
        }

        @Override
        public String toString() {
            return "FieldMeTa{" +
                    "fieldType='" + fieldType + '\'' +
                    ", filedSize=" + filedSize +
                    ", note='" + note + '\'' +
                    ", nullable=" + nullable +
                    '}';
        }
    }


    private Map<String,FieldMeTa> fieldMap;//表字段 -key:字段名 -value:字段附加信息(类型,注释,是否为空等)
    private String tableName;//表名

    public TableInfo() {
    }

    public TableInfo(Map<String, FieldMeTa> fieldMap, String tableName) {
        this.fieldMap = fieldMap;
        this.tableName = tableName;
    }

    public Map<String, FieldMeTa> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, FieldMeTa> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "fieldMap=" + fieldMap +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
