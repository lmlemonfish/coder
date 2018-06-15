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

import java.util.Date;
import java.util.HashMap;

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
        CommonDBService service = engine.getCommonDBService("Mysql");
        //连接数据库
        service.checkConnection("jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf8&useInformationSchema=true", "root", "root");
        //取表信息
        TableInfo info = service.getTableInfoFromDB("tb_content");

        //设置表信息
        HashMap<String,Object> valueMap = new HashMap<>();
        FileFromDbData fileFromDbData = new FileFromDbData();

        //设置类基本信息 (命名规范等)
        ClassBasicData basicData = setClassBasicData();
        basicData.setClassName(info.convertTableName());
        basicData.setDate(new Date());
        basicData.setAuthor("LM");
        basicData.setDescription(info.getNote());

        MethodBasicData dao = new MethodBasicData();
        dao.setPackageName(basicData.getPackageName());
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
        dao.setBatchAddMethod("batchAdd");

        fileFromDbData.setTableInfo(info);
        fileFromDbData.setClassInfo(basicData);
        fileFromDbData.setDao(dao);

        valueMap.put(BuildEnum.TEMPLATE.getModelKey(),fileFromDbData);
        //建造者传入指挥者 --> 生成模板
        TemplateFromDbBuilder builder = new TemplateFromDbBuilder(valueMap,"E:/TestDemo.java");
        TemplateDirector director = new TemplateDirector(builder);
        director.buildEntityTemplate();
    }

    private ClassBasicData setClassBasicData() {
        ClassBasicData classData = new ClassBasicData();
        classData.setAuthor("LM");
        classData.setPackageName("com.test");
        return classData;
    }
}
