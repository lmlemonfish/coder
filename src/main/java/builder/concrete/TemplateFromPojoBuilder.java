package builder.concrete;

import java.util.Map;

import builder.TemplateBuilder;
import constants.BuildEnum;
import utils.FreemarkerUtil;

/**
 * 
 * @author ma
 *
 */
public class TemplateFromPojoBuilder extends TemplateBuilder {

	public TemplateFromPojoBuilder(Map<String, Object> valueMap, String path) {
		super(valueMap, path);
	}

	@Override
	public void builderEntity() {
		FreemarkerUtil.produceFile("entity.ftl", getValueMap(), getPath());
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
