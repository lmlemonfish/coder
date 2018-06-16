package builder.concrete;

import builder.TemplateBuilder;
import constants.BuildEnum;
import entity.FileFromDbData;
import entity.TableInfo;
import utils.FreemarkerUtil;

import java.util.Map;

/**
 * @ClassName TemplateFromDbBuilder
 * @Description TODO 从数据库中build模板
 * @Author LM
 * @Date 2018/6/10 0010 上午 11:18
 * @Version 1.0
 **/
public class TemplateFromDbBuilder extends TemplateBuilder {


    //父类提供了valueMap,与文件路径path
    public TemplateFromDbBuilder(Map<String, Object> valueMap, String path) {
        super(valueMap, path);
    }

    @Override
    public void buildEntity() {
        //转换后的实体 info,方便后面mapper的生成
        TableInfo data = new TableInfo();
        //获取valueMap数据
        FileFromDbData fileFromDbData = (FileFromDbData)(getValueMap().get(BuildEnum.TEMPLATE.getModelKey()));
        TableInfo info = fileFromDbData.getTableInfo();
        //clone
        //转化为java类型
        data.setFieldList(info.convert());
        data.setNote(info.getNote());
        data.setTableName(info.getTableName());

        fileFromDbData.setEntity(data);

        //getValueMap().put(BuildEnum.TEMPLATE.getModelKey(),fileFromDbData);

        String path = getPath();
        //判断数据的有效性
        checkNeedfulData();

        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.ENTITY.getFtlByDbName(), getValueMap(), path);


    }

    @Override
    public void buildMapper() {
        String path = getPath();
        //TODO path需要解析
        path = "E:/TestDemoMapper.xml";
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.MAPPER.getFtlByPojoName(), getValueMap(), path);
    }

    @Override
    public void buildDao() {
        String path = getPath();
        //TODO path需要解析 , 需要注意:mapper的生成namespace要依赖dao的包名
        path = "E:/TestDemoDao.java";
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.DAO.getFtlByPojoName(), getValueMap(), path);
    }

    @Override
    public void buildService() {
        String path = getPath();
        //TODO path需要解析
        path = "E:/TestDemoService.java";
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.SERVICE.getFtlByPojoName(), getValueMap(), path);
    }

    @Override
    public void buildServiceImpl() {
        String path = getPath();
        //TODO path需要解析
        path = "E:/TestDemoServiceImpl.java";
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.SERVICE_IMPL.getFtlByPojoName(), getValueMap(), path);
    }

    @Override
    public void buildController() {
        String path = getPath();
        //TODO path需要解析
        path = "E:/TestDemoController.java";
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.Controller.getFtlByPojoName(), getValueMap(), path);
    }
}
