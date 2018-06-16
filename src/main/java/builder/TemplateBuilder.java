package builder;

import com.google.common.base.Strings;
import exception.BusinessException;

import java.util.Map;


/**
 * @ClassName TemplateBuilder
 * @Description TODO 模板-父类建造者
 * @Author LM
 * @Date 2018/6/10 0010 上午 11:01
 * @Version 1.0
 **/
public abstract class TemplateBuilder {

    //数据-model
    Map<String,Object> valueMap;
    //输出文件路径
    String path;


    public TemplateBuilder(Map<String, Object> valueMap, String path) {
        this.valueMap = valueMap;
        this.path = path;
    }

    /**
     * 检查valueMap与path成员变量的有效性,直接抛出异常
     * (TODO 1.0V之后 考虑到子类build中可能需要做大量的判断,判断放入get方法中)
     */
    public void checkNeedfulData() {
        if(valueMap == null) {
            throw new BusinessException("build模板,数据不能为空!");
        }
        if(Strings.isNullOrEmpty(path)) {
            throw new BusinessException("输出文件路径不能为空!");
        }
    }

    public abstract void buildEntity();

    public abstract void buildMapper();

    public abstract void buildDao();

    public abstract void buildService();

    public abstract void buildServiceImpl();

    public abstract void buildController();

    public Map<String, Object> getValueMap() { return valueMap; }

    public void setValueMap(Map<String, Object> valueMap) {
        this.valueMap = valueMap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
