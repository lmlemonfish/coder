package file;

import constants.BuildEnum;
import org.junit.Test;

import java.io.File;

/**
 * @ClassName FileFindApp
 * @Description TODO
 * @Author LM
 * @Date 2018/6/16 0016 下午 4:47
 * @Version 1.0
 **/
public class FileFindApp {

    @Test
    public void testFile() {

        //思路:
        //1.判断是否为Maven工程,是否为Maven多模块父工程
        //2.解析包名
        //3.根据包名放置生成模板的位置

        boolean isMavenPro = false;

        String packagePre = "com.lmfish";
        String path = "F:/iwork/coder";

        String packageModel = "test";
        String type = BuildEnum.DAO.getModelKey();

        String filePath = null;

        //1
        File file = new File(path + "/src");
        String[] list = file.list();
        for (String item : list) {
            if (item.equals("main")) {
                isMavenPro = true;
                break;
            }
        }
        if(isMavenPro) {
            //maven工程
            filePath = path + "/src/main/java";
        } else {
            filePath = path + "/src/java";
        }
        //2
        String[] split = packagePre.split("\\.");

        //拼接前缀名
        for (String item : split) {
            filePath += "/" + item;
        }
        //拼接包名
        filePath += "/" + packageModel;
        //拼接模板类型(Model名)
        filePath += "/" + type;

        //判断是否存在该目录
        File rootFile = new File(filePath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }

        //3


    }
}
