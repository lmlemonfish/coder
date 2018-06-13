package entity;

import com.google.common.base.CaseFormat;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import constants.DicConstants;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TableInfo
 * @Description TODO 数据库表信息pojo
 * @Author LM
 * @Date 2018/6/6 0006 下午 5:57
 * @Version 1.0
 **/
public class TableInfo {

    //内部类,表示表字段的附加信息
    public class FieldMeTa{
        private String fieldName;
        private String fieldType;
        private Integer filedSize;
        private String note;
        private Integer nullable;

        public FieldMeTa() {
        }

        public FieldMeTa(String fieldName, String fieldType, Integer filedSize, String note, Integer nullable) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.filedSize = filedSize;
            this.note = note;
            this.nullable = nullable;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
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
                    "fieldName='" + fieldName + '\'' +
                    ", fieldType='" + fieldType + '\'' +
                    ", filedSize=" + filedSize +
                    ", note='" + note + '\'' +
                    ", nullable=" + nullable +
                    '}';
        }
    }


    private List<FieldMeTa> fieldList;//表字段 -key:字段名 -value:字段附加信息(类型,注释,是否为空等)
    private String tableName;//表名
    private String note;//表注释

    public TableInfo() {
    }

    public TableInfo(List<FieldMeTa> fieldList, String tableName, String note) {
        this.fieldList = fieldList;
        this.tableName = tableName;
        this.note = note;
    }

    /**
     * 1.将tableInfo中的数据库类型字段 转化为 java类型
     * 2.将a_b这种形式,转化为驼峰 aB
     * @return
     */
    public List<FieldMeTa> convert() {

        DicConstants dicConstants = DicConstants.getInstance();
        HashMap<String, String> map = dicConstants.getMap();

        /*List<FieldMeTa> list = new ArrayList<>();

        for (FieldMeTa input: fieldList) {
            String fieldName = input.getFieldName();
            String type = input.getFieldType();
            String newType = map.get(type);
            input.setFieldType(newType);

            //将a_b这种形式,转化为驼峰 aB
            String formatName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, fieldName);
            input.setFieldName(formatName);
        }*/

        List<FieldMeTa> list = Lists.transform(fieldList, new Function<FieldMeTa, FieldMeTa>() {
            @Override
            public FieldMeTa apply(FieldMeTa input) {

                FieldMeTa fieldMeTa = new FieldMeTa();
                fieldMeTa.setNote(input.getNote());
                fieldMeTa.setNullable(input.getNullable());

                String fieldName = input.getFieldName();
                String type = input.getFieldType();
                String newType = map.get(type);
                fieldMeTa.setFieldType(newType);

                //将a_b这种形式,转化为驼峰 aB
                String formatName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, fieldName);
                fieldMeTa.setFieldName(formatName);

                return fieldMeTa;
            }
        });

        return list;

        //TODO guava的坑
        //return Lists.newArrayList(list);
        //fieldList = list;

    }

    public String convertTableName() {
        //表名转化为驼峰形式输出 如tb_user --> tbUser
        //ToDo 可考虑去掉前面的'tb'字符串
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,tableName);
    }

    public List<FieldMeTa> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldMeTa> fieldList) {
        this.fieldList = fieldList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "fieldList=" + fieldList +
                ", tableName='" + tableName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
