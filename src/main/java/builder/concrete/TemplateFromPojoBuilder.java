package builder.concrete;

import builder.TemplateBuilder;
import utils.FreemarkerUtil;

import java.util.Map;

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
	public void buildEntity() {
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
	public void buildServiceImpl() {

	}

	@Override
	public void buildController() {

	}

}
