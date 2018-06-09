package entity;

import entity.basic.ClassBasicData;
import entity.basic.ServiceBasicData;

/**
 * 封装生成的实体类、业务处理类(controllor、service、dao)或xml
 * 
 * @author ma
 *
 */
public class ServiceFileData {
	private EntityData entity; // 生成的实体
	private ServiceBasicData controllor; // 生成的controllor
	private ServiceBasicData service; // 生成的service
	private ServiceBasicData dao; // 生成的dao
	// 生成的mapper.xml

	public ServiceBasicData getControllor() {
		return controllor;
	}

	public EntityData getEntity() {
		return entity;
	}

	public void setEntity(EntityData entity) {
		this.entity = entity;
	}

	public void setControllor(ServiceBasicData controllor) {
		this.controllor = controllor;
	}

	public ServiceBasicData getService() {
		return service;
	}

	public void setService(ServiceBasicData service) {
		this.service = service;
	}

	public ServiceBasicData getDao() {
		return dao;
	}

	public void setDao(ServiceBasicData dao) {
		this.dao = dao;
	}

}
