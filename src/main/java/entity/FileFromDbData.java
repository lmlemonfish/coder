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
    private MethodBasicData serviceImpl; // 生成的serviceImpl
    private MethodBasicData dao; // 生成的dao
    private MethodBasicData mapper; // 生成的dao
    private String packagePre;// 包名前缀 如:com.lm

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

    public MethodBasicData getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(MethodBasicData serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public MethodBasicData getMapper() {
        return mapper;
    }

    public void setMapper(MethodBasicData mapper) {
        this.mapper = mapper;
    }

    public MethodBasicData getDao() {
        return dao;
    }

    public void setDao(MethodBasicData dao) {
        this.dao = dao;
    }

    public String getPackagePre() {
        return packagePre;
    }

    public void setPackagePre(String packagePre) {
        this.packagePre = packagePre;
    }
}
