package utilstest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import entity.EntityData;
import entity.ServiceFileData;
import entity.basic.ServiceBasicData;
import utils.Constants;
import utils.FreemarkerUtil;

public class TestFreemarker {
	
	@Test
	public void test1() {
		Map<String, Object> valueMap = new HashMap<>();
		
		EntityData entity = new EntityData();
		entity.setClassName("TestDemo");
		entity.setDescription("hello world test");
		entity.setPackageName("util");
		Map<String, String> attrs = new HashMap<>();
		attrs.put("id", "");
		attrs.put("age", "年龄");
		
		entity.setIntegerMap(attrs);
		
		Map<String, String> strs = new HashMap<>();
		strs.put("name", "姓名");
		strs.put("address", "地址");
		
		entity.setStrMap(strs);
		
		Map<String, String> dateMap = new HashMap<>();
		dateMap.put("createTime", "创建时间");
		
		entity.setDateMap(dateMap);
		
		Map<String, String> listMap = new HashMap<>();
		listMap.put("list", "");
		entity.setListMap(listMap);
		/*List<String> attrs2 = new ArrayList<>();
		attrs2.add("name");
		attrs2.add("address");
		entity.setIntegerList(attrs);
		entity.setStrList(attrs2);
		
		List<String> dateList = new ArrayList<>();
		dateList.add("createTime");
		entity.setDateList(dateList);*/
		
		valueMap.put("entity", entity);
		
		FreemarkerUtil.produceFile("entity.ftl", valueMap, "E:/TestDemo.java");
	}

	@Test
	public void test2() {
		Map<String, Object> valueMap = new HashMap<>();
		
		EntityData entity = new EntityData();
		entity.setClassName("TestEntity");
		entity.setDescription("test entity");
		entity.setPackageName("utils");
		Map<String, String> attrs = new HashMap<>();
		attrs.put("id", "");
		attrs.put("age", "年龄");
		
		entity.setIntegerMap(attrs);
		
		Map<String, String> strs = new HashMap<>();
		strs.put("name", "姓名");
		strs.put("address", "地址");
		
		entity.setStrMap(strs);
		
		Map<String, String> dateMap = new HashMap<>();
		//dateMap.put("createTime", "创建时间");
		
		entity.setDateMap(dateMap);
		
		Map<String, String> listMap = new HashMap<>();
		listMap.put("list", "");
		entity.setListMap(listMap);
		
		valueMap.put("entity", entity);
		
		ServiceBasicData dao = new ServiceBasicData();
		dao.setPackageName(entity.getPackageName());
		dao.setClassName("TestDao");
		dao.setDescription("test dao");
		dao.setAuthor("柳明兄");
		dao.setAddMethod("save");
		dao.setUpdateMethod("update");
		dao.setDelMethod("delById");
		dao.setFindByIdMethod("loadById");
		dao.setDelBatchMethod("delBatchById");
		dao.setUpdateBatchMethod("updateBatch");
		dao.setFindAllMethod("list");
		
		ServiceBasicData service = new ServiceBasicData();
		service.setPackageName(entity.getPackageName());
		service.setClassName("TestService");
		service.setDescription("test service");
		service.setAuthor("柳明兄");
		service.setAddMethod("save");
		service.setUpdateMethod("update");
		service.setDelMethod("delById");
		service.setFindByIdMethod("loadById");
		service.setDelBatchMethod("delBatchById");
		service.setUpdateBatchMethod("updateBatch");
		service.setFindAllMethod("list");
		
		ServiceBasicData controllor = new ServiceBasicData();
		controllor.setPackageName(entity.getPackageName());
		controllor.setClassName("TestControllor");
		controllor.setDescription("test controllor");
		controllor.setAuthor("柳明兄");
		controllor.setAddMethod("save");
		controllor.setUpdateMethod("update");
		controllor.setDelMethod("delById");
		controllor.setFindByIdMethod("loadById");
		controllor.setDelBatchMethod("delBatchById");
		controllor.setUpdateBatchMethod("updateBatch");
		controllor.setFindAllMethod("list");
		

		ServiceFileData serviceBasic = new ServiceFileData();
		serviceBasic.setControllor(controllor);
		serviceBasic.setService(service);
		serviceBasic.setEntity(entity);
		serviceBasic.setDao(dao);
		valueMap.put("serviceBasic", serviceBasic);
		valueMap.put(Constants.IS_USE_SPRING_ANNOTATION, Constants.USE_SPRING_ANNOTATION);
		
		
		FreemarkerUtil.produceFile("entity.ftl", valueMap, "E:/TestEntity.java");
		FreemarkerUtil.produceFile("dao.ftl", valueMap, "E:/TestDao.java");
		FreemarkerUtil.produceFile("service.ftl", valueMap, "E:/TestService.java");
		FreemarkerUtil.produceFile("serviceImpl.ftl", valueMap, "E:/TestServiceImpl.java");
		FreemarkerUtil.produceFile("controllor.ftl", valueMap, "E:/TestControllor.java");
	}
}
