package builder.director;

import builder.TemplateBuilder;

/**
 * @ClassName TemplateDirector
 * @Description TODO 建造模板 -- 指挥者
 * @Author LM
 * @Date 2018/6/10 0010 上午 11:14
 * @Version 1.0
 **/
public class TemplateDirector {

    private TemplateBuilder builder;

    public TemplateDirector(TemplateBuilder builder) {
        this.builder = builder;
    }

    public void buildEntityTemplate() {

        builder.builderEntity();
        builder.buildMapper();
    }
    
    public void buildEntityFile() {
    	builder.builderEntity();
    }

}
