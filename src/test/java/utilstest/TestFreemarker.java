package utilstest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.EntityMetaData;
import utils.FreemarkerUtil;

public class TestFreemarker {

	public static void main(String[] args) {
		Map<String, Object> valueMap = new HashMap<>();
		
		EntityMetaData entity = new EntityMetaData();
		entity.setClassName("TestDemo");
		entity.setDescription("hello world test");
		entity.setPackageName("com.hui");
		List<String> attrs = new ArrayList<>();
		attrs.add("id");
		attrs.add("age");
		entity.setIntegerList(attrs);
		
		List<String> attrs2 = new ArrayList<>();
		attrs2.add("name");
		attrs2.add("address");
		entity.setIntegerList(attrs);
		entity.setStrList(attrs2);
		
		List<String> dateList = new ArrayList<>();
		dateList.add("createTime");
		entity.setDateList(dateList);
		
		valueMap.put("entity", entity);
		
		FreemarkerUtil.produceFile("entity.ftl", valueMap, "E:/TestDemo.java");
	}

}
