package builder.concrete;

import builder.TemplateBuilder;
import constants.BuildEnum;
import constants.FileConstants;
import entity.FileFromDbData;
import entity.TableInfo;
import utils.FilePathGenUtils;
import utils.FreemarkerUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TemplateFromDbBuilder
 * @Description TODO 从数据库中build模板
 * @Author LM
 * @Date 2018/6/10 0010 上午 11:18
 * @Version 1.0
 **/
public class TemplateFromDbBuilder extends TemplateBuilder {

    //java文件后缀
    private final String fileJavaSuffix = ".java";
    //mapper文件后缀
    private final String fileMapperSuffix = ".xml";

    private FileFromDbData fileFromDbData = (FileFromDbData)(getValueMap().get(BuildEnum.TEMPLATE.getModelKey()));

    //父类提供了valueMap,与文件路径path
    public TemplateFromDbBuilder(Map<String, Object> valueMap, String path) {
        super(valueMap, path);
    }

    @Override
    public void buildEntity() {
        //转换后的实体 info,方便后面mapper的生成
        TableInfo data = new TableInfo();
        //获取valueMap数据
        TableInfo info = fileFromDbData.getTableInfo();
        //clone
        //转化为java类型
        data.setFieldList(info.convert());
        data.setNote(info.getNote());
        data.setTableName(info.getTableName());

        fileFromDbData.setEntity(data);

        //getValueMap().put(BuildEnum.TEMPLATE.getModelKey(),fileFromDbData);

        String path = getPath();
        String fileName = "/" + fileFromDbData.getClassInfo().getClassName() + fileJavaSuffix;
        //解析path
        String packageModel = fileFromDbData.getClassInfo().getPackageName();
        String packagePre = fileFromDbData.getPackagePre();
        HashMap<String, String> map = FilePathGenUtils.getGenFilePath(path, packageModel, packagePre, BuildEnum.ENTITY.getModelKey());
        String genFilePath = map.get(FileConstants.FILE_PATH_KEY);
        String genPackage = map.get(FileConstants.PAGE_NAME_KEY);
        //设置新的包名
        fileFromDbData.getClassInfo().setPackageName(genPackage);
        //判断数据的有效性
        checkNeedfulData();

        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.ENTITY.getFtlByDbName(), getValueMap(), genFilePath + fileName);


    }

    @Override
    public void buildMapper() {
        String path = getPath();
        String fileName = "/" + fileFromDbData.getMapper().getClassName() + fileMapperSuffix;
        //解析path
        String packageModel = fileFromDbData.getMapper().getPackageName();
        String packagePre = fileFromDbData.getPackagePre();
        HashMap<String, String> map = FilePathGenUtils.getGenFilePath(path, packageModel, packagePre, BuildEnum.DAO.getModelKey());
        String genFilePath = map.get(FileConstants.FILE_PATH_KEY);
        String genPackage = map.get(FileConstants.PAGE_NAME_KEY);

        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.MAPPER.getFtlByPojoName(), getValueMap(), genFilePath + fileName);
    }

    @Override
    public void buildDao() {
        String path = getPath();
        String fileName = "/" + fileFromDbData.getDao().getClassName() + fileJavaSuffix;
        //解析path
        String packageModel = fileFromDbData.getDao().getPackageName();
        String packagePre = fileFromDbData.getPackagePre();
        HashMap<String, String> map = FilePathGenUtils.getGenFilePath(path, packageModel, packagePre, BuildEnum.DAO.getModelKey());
        String genFilePath = map.get(FileConstants.FILE_PATH_KEY);
        String genPackage = map.get(FileConstants.PAGE_NAME_KEY);
        //设置新的包名
        fileFromDbData.getDao().setPackageName(genPackage);
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.DAO.getFtlByPojoName(), getValueMap(), genFilePath + fileName);
    }

    @Override
    public void buildService() {
        String path = getPath();
        String fileName = "/" + fileFromDbData.getService().getClassName() + fileJavaSuffix;
        //解析path
        String packageModel = fileFromDbData.getService().getPackageName();
        String packagePre = fileFromDbData.getPackagePre();
        HashMap<String, String> map = FilePathGenUtils.getGenFilePath(path, packageModel, packagePre, BuildEnum.SERVICE.getModelKey());
        String genFilePath = map.get(FileConstants.FILE_PATH_KEY);
        String genPackage = map.get(FileConstants.PAGE_NAME_KEY);
        //设置新的包名
        fileFromDbData.getService().setPackageName(genPackage);
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.SERVICE.getFtlByPojoName(), getValueMap(), genFilePath + fileName);
    }

    @Override
    public void buildServiceImpl() {
        String path = getPath();
        String fileName = "/" + fileFromDbData.getServiceImpl().getClassName() + fileJavaSuffix;
        //解析path
        String packageModel = fileFromDbData.getServiceImpl().getPackageName();
        String packagePre = fileFromDbData.getPackagePre();
        HashMap<String, String> map = FilePathGenUtils.getGenFilePath(path, packageModel, packagePre, BuildEnum.SERVICE.getModelKey() + ".impl");
        String genFilePath = map.get(FileConstants.FILE_PATH_KEY);
        String genPackage = map.get(FileConstants.PAGE_NAME_KEY);
        //设置新的包名
        fileFromDbData.getServiceImpl().setPackageName(genPackage);
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.SERVICE_IMPL.getFtlByPojoName(), getValueMap(), genFilePath + fileName);
    }

    @Override
    public void buildController() {
        String path = getPath();
        String fileName = "/" + fileFromDbData.getControllor().getClassName() + fileJavaSuffix;
        //解析path
        String packageModel = fileFromDbData.getControllor().getPackageName();
        String packagePre = fileFromDbData.getPackagePre();
        HashMap<String, String> map = FilePathGenUtils.getGenFilePath(path, packageModel, packagePre, BuildEnum.Controller.getModelKey());
        String genFilePath = map.get(FileConstants.FILE_PATH_KEY);
        String genPackage = map.get(FileConstants.PAGE_NAME_KEY);
        //设置新的包名
        fileFromDbData.getControllor().setPackageName(genPackage);
        //判断数据的有效性
        checkNeedfulData();
        //freemarket
        FreemarkerUtil.produceFileByPojo(BuildEnum.Controller.getFtlByPojoName(), getValueMap(), genFilePath + fileName);
    }
}
