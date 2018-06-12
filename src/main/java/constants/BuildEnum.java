package constants;

/**
 * @ClassName BuildEnum
 * @Description TODO
 * @Author LM
 * @Date 2018/6/10 0010 下午 8:38
 * @Version 1.0
 **/
public enum BuildEnum {

    CLASSINFO("classInfo","null","null"),
    ENTITY("entity", "entity.ftl", "db_entity.ftl"),
    DAO("dao", "dao.ftl", "db_dao.ftl"),
    SERVICE("service", "service.ftl", "db_service.ftl"),
    SERVICE_IMPL("serviceImpl", "serviceImpl.ftl", "db_serviceImpl.ftl"),
    Controller("controller", "controllor.ftl", "db_controllor.ftl"),
    BASE_ENTITY("baseEntity", "baseEntity.ftl", "db_baseEntity.ftl"),
    BASE_DAO("baseDao", "baseDao.ftl", "db_baseDao.ftl"),
    BASE_SERVICE("baseService", "baseService.ftl", "db_baseService.ftl"),
    BASE_CONTROLLER("baseController", "baseController.ftl", "db_baseController.ftl");

    //建造者builder,需要通过map的key拿到模板数据,modelKey为map中的key值
    private String modelKey;
    //从数据库生成模板 与 从pojo生成模板,模板是不同的.
    //pojo方式
    private String ftlByPojoName;
    //db方式
    private String ftlByDbName;

    BuildEnum(String modelKey, String ftlByPojoName, String ftlByDbName) {
        this.modelKey = modelKey;
        this.ftlByPojoName = ftlByPojoName;
        this.ftlByDbName = ftlByDbName;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public String getFtlByPojoName() {
        return ftlByPojoName;
    }

    public void setFtlByPojoName(String ftlByPojoName) {
        this.ftlByPojoName = ftlByPojoName;
    }

    public String getFtlByDbName() {
        return ftlByDbName;
    }

    public void setFtlByDbName(String ftlByDbName) {
        this.ftlByDbName = ftlByDbName;
    }
}
