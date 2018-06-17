package utils;

import constants.FileConstants;

import java.io.File;
import java.util.HashMap;

/**
 * @ClassName FilePathGenUtils
 * @Description TODO
 * @Author LM
 * @Date 2018/6/17 0017 下午 2:22
 * @Version 1.0
 **/
public class FilePathGenUtils {

    /**
     * 模块名与模块前缀(groupId)分开,是因为单模块与Maven多模块的结构是不同的
     * @param path 项目路径
     * @param packageModel 模块名
     * @param packagePre 模块前缀(如 com.ccsu)
     * @param modelType 模板类型(dao,service,entity,controller,mapper,impl等)
     * @return map包含生成文件路径,和对应的包名
     */
    public static HashMap<String,String> getGenFilePath(String path, String packageModel, String packagePre, String modelType) {

        HashMap<String, String> map = new HashMap<>();
        String filePath = null;
        String packFinalName = null;
        boolean isMavenPro = false;

        //1.判断是否为Maven工程,是否为Maven多模块父工程
        File file = new File(path + "/src");
        String[] list = file.list();
        if(list != null) {
            for (String item : list) {
                if (item.equals("main")) {
                    isMavenPro = true;
                    break;
                }
            }
        }
        //TODO 之后会加入Maven多模块父工程,该结构与单模块不同
        if(isMavenPro) {
            //maven工程
            filePath = path + "/src/main/java";
        } else {
            filePath = path + "/src/java";
        }
        //拼接包名
        packFinalName = packagePre + "." + packageModel + "." + modelType;

        //2.解析包名
        String[] split = packFinalName.split("\\.");

        //拼接前缀名
        for (String item : split) {
            filePath += "/" + item;
        }
        //拼接包名
        //filePath += "/" + packageModel;
        //拼接模板类型(Model名)
        //filePath += "/" + modelType;

        //判断是否存在该目录
        File rootFile = new File(filePath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        //3.根据包名放置生成模板的位置,返回生成路径和包名
        map.put(FileConstants.FILE_PATH_KEY,filePath);
        map.put(FileConstants.PAGE_NAME_KEY,packFinalName);
        return map;
    }

}
