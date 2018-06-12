package entity;

import entity.basic.MethodBasicData;

/**
 * 封装生成的实体类、业务处理类(controllor、service、dao)或xml
 * 
 * @author ma
 *
 */
public class FileFromPojoData {
	private EntityData entity; // 生成的实体
	private MethodBasicData controllor; // 生成的controllor
	private MethodBasicData service; // 生成的service
	private MethodBasicData dao; // 生成的dao
	// 生成的mapper.xml

	public MethodBasicData getControllor() {
		return controllor;
	}

	public EntityData getEntity() {
		return entity;
	}

	public void setEntity(EntityData entity) {
		this.entity = entity;
	}

	public void setControllor(MethodBasicData controllor) {
		this.controllor = controllor;
	}

	public MethodBasicData getService() {
		return service;
	}

	public void setService(MethodBasicData service) {
		this.service = service;
	}

	public MethodBasicData getDao() {
		return dao;
	}

	public void setDao(MethodBasicData dao) {
		this.dao = dao;
	}

}
