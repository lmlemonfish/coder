package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * freemarket模板工具类
 * @author ma
 *
 */
public class FreemarkerUtil {

	private static Configuration configuration;
	
	static {
		configuration = new Configuration(Configuration.VERSION_2_3_23);
		
		configuration.setClassForTemplateLoading(FreemarkerUtil.class, "/ftl");
	}
	
	public static Template getTemplate(String ftlName) {
		Template template = null;
		try {
			template = configuration.getTemplate(ftlName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return template;
	}
	
	/**
	 * 根据模板生成文件
	 */
	public static void produceFile(String ftlName, Map<String, Object> valueMap, String file) {
		File f = new File(file);
		
		try(FileWriter out = new FileWriter(f)){
			Template template = getTemplate(ftlName);
			template.process(valueMap, out);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
