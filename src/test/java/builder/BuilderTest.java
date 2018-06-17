package builder;

import builder.concrete.TemplateFromDbBuilder;
import builder.director.TemplateDirector;
import constants.BuildEnum;
import db.service.CommonDBService;
import engine.CoderEngine;
import entity.FileFromDbData;
import entity.TableInfo;
import entity.basic.ClassBasicData;
import entity.basic.MethodBasicData;
import org.junit.Test;
import utils.Constants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BuilderTest
 * @Description TODO
 * @Author LM
 * @Date 2018/6/11 0011 下午 4:51
 * @Version 1.0
 **/
public class BuilderTest {

    @Test
    public void buildBaseTest() {
        //拿到Coder引擎
        CoderEngine engine = new CoderEngine();
        // 拿到service
        CommonDBService dbService = engine.getCommonDBService("Mysql");
        //连接数据库
        dbService.checkConnection("jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf8&useInformationSchema=true", "root", "root");
        //取表信息
        TableInfo info = dbService.getTableInfoFromDB("tb_content");

        //设置表信息
        HashMap<String,Object> valueMap = new HashMap<>();
        FileFromDbData fileFromDbData = new FileFromDbData();

        //设置类基本信息 (命名规范等)
        ClassBasicData basicData = setClassBasicData();
        basicData.setClassName(info.convertTableName());
        basicData.setDate(new Date());
        basicData.setAuthor("LM");
        basicData.setDescription(info.getNote());

        MethodBasicData dao = setBuildDao(valueMap);
        MethodBasicData mapper = setBuildDao(valueMap);
        MethodBasicData service = setBuildService(valueMap);
        MethodBasicData serviceImpl = setBuildService(valueMap);
        MethodBasicData controller = setBuildController(valueMap);

        fileFromDbData.setTableInfo(info);
        fileFromDbData.setClassInfo(basicData);
        fileFromDbData.setDao(dao);
        fileFromDbData.setMapper(mapper);
        fileFromDbData.setServiceImpl(serviceImpl);
        fileFromDbData.setService(service);
        fileFromDbData.setControllor(controller);

        fileFromDbData.setPackagePre("com.lm");

        valueMap.put(BuildEnum.TEMPLATE.getModelKey(),fileFromDbData);
        valueMap.put(Constants.IS_USE_SPRING_ANNOTATION, Constants.USE_SPRING_ANNOTATION);
        //建造者传入指挥者 --> 生成模板
        TemplateFromDbBuilder builder = new TemplateFromDbBuilder(valueMap,"E:/lmtest");
        TemplateDirector director = new TemplateDirector(builder);
        director.buildEntityTemplate();
    }

    private ClassBasicData setClassBasicData() {
        ClassBasicData classData = new ClassBasicData();
        classData.setAuthor("LM");
        classData.setPackageName("test");
        return classData;
    }



    private MethodBasicData setBuildDao(Map map) {
        MethodBasicData dao = new MethodBasicData();
        dao.setPackageName("test");
        dao.setClassName("TestDao");
        dao.setDescription("test dao");
        dao.setAuthor("LM");
        dao.setAddMethod("save");
        dao.setUpdateMethod("update");
        dao.setDelMethod("delById");
        dao.setFindByIdMethod("loadById");
        dao.setDelBatchMethod("delBatchById");
        dao.setUpdateBatchMethod("updateBatch");
        dao.setFindAllMethod("list");
        dao.setAddBatchMethod("addBatch");
        return dao;
    }

    private MethodBasicData setBuildMapper(Map map) {
        MethodBasicData mapper = new MethodBasicData();
        mapper.setPackageName("test");
        mapper.setClassName("TestMapper");
        mapper.setDescription("test mapper");
        mapper.setAuthor("LM");
        return mapper;
    }

    private MethodBasicData setBuildService(Map map) {
        MethodBasicData service = new MethodBasicData();
        service.setPackageName("test");
        service.setClassName("TestService");
        service.setDescription("test service");
        service.setAuthor("LM");
        service.setAddMethod("save");
        service.setUpdateMethod("update");
        service.setDelMethod("delById");
        service.setFindByIdMethod("loadById");
        service.setDelBatchMethod("delBatchById");
        service.setUpdateBatchMethod("updateBatch");
        service.setFindAllMethod("list");
        service.setAddBatchMethod("addBatch");
        return service;
    }

    private MethodBasicData setBuildServiceImpl(Map map) {
        MethodBasicData serviceImpl = new MethodBasicData();
        serviceImpl.setPackageName("test");
        serviceImpl.setClassName("TestServiceImpl");
        serviceImpl.setDescription("test serviceImpl");
        serviceImpl.setAuthor("LM");
        return serviceImpl;
    }

    private MethodBasicData setBuildController(Map map) {
        MethodBasicData controller = new MethodBasicData();
        controller.setPackageName("test");
        controller.setClassName("TestController");
        controller.setDescription("test controller");
        controller.setAuthor("LM");
        controller.setAddMethod("save");
        controller.setUpdateMethod("update");
        controller.setDelMethod("delById");
        controller.setFindByIdMethod("loadById");
        controller.setDelBatchMethod("delBatchById");
        controller.setUpdateBatchMethod("updateBatch");
        controller.setFindAllMethod("list");
        controller.setAddBatchMethod("addBatch");
        return controller;
    }
}
