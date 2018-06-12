package builder.concrete;

import builder.TemplateBuilder;
import constants.BuildEnum;
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
    public void builderEntity() {
        TableInfo data = null;
        //获取valueMap数据
        //Object o = getValueMap().get(BuildEnum.ENTITY.getModelKey());
        String path = getPath();
        //判断数据的有效性
        checkNeedfulData();
        //判断类型
        /*if (o instanceof TableInfo) {
            data = (TableInfo) o;
        }*/

        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.ENTITY.getFtlByDbName(), getValueMap(), path);


    }

    @Override
    public void buildMapper() {

    }

    @Override
    public void buildDao() {

    }

    @Override
    public void buildService() {

    }

    @Override
    public void buildController() {

    }
}
