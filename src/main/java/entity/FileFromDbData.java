package entity;

import entity.basic.ClassBasicData;
import entity.basic.MethodBasicData;

/**
 * @ClassName FileFromDbData
 * @Description TODO
 * @Author LM
 * @Date 2018/6/13 0013 上午 11:18
 * @Version 1.0
 **/
public class FileFromDbData {
    private TableInfo tableInfo;
    private TableInfo entity;
    private ClassBasicData classInfo;
    private MethodBasicData controllor; // 生成的controllor
    private MethodBasicData service; // 生成的service
    private MethodBasicData dao; // 生成的dao

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public TableInfo getEntity() {
        return entity;
    }

    public void setEntity(TableInfo entity) {
        this.entity = entity;
    }

    public ClassBasicData getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassBasicData classInfo) {
        this.classInfo = classInfo;
    }

    public MethodBasicData getControllor() {
        return controllor;
    }

    public void setControllor(MethodBasicData controllor) {
        this.controllor = controllor;
    }

    public MethodBasicData getService() {
        return service;
    }

    public void setService(MethodBasicData service) {
        this.service = service;
    }

    public MethodBasicData getDao() {
        return dao;
    }

    public void setDao(MethodBasicData dao) {
        this.dao = dao;
    }
}
