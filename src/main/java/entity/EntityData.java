package entity;

import java.util.Map;

import entity.basic.ClassBasicData;

/**
 * 封装生成实体的信息
 * 
 * @author ma
 *
 */
public class EntityData extends ClassBasicData {
	private Map<String, String> integerMap; // 存放int类型属性以及对应的注释信息
	private Map<String, String> longMap;
	private Map<String, String> doubleMap;
	private Map<String, String> floatMap;
	private Map<String, String> strMap;
	private Map<String, String> dateMap;
	private Map<String, String> listMap;
	private Map<String, String> setMap;
	private Map<String, String> mMap;

	public Map<String, String> getIntegerMap() {
		return integerMap;
	}

	public void setIntegerMap(Map<String, String> integerMap) {
		this.integerMap = integerMap;
	}

	public Map<String, String> getLongMap() {
		return longMap;
	}

	public void setLongMap(Map<String, String> longMap) {
		this.longMap = longMap;
	}

	public Map<String, String> getDoubleMap() {
		return doubleMap;
	}

	public void setDoubleMap(Map<String, String> doubleMap) {
		this.doubleMap = doubleMap;
	}

	public Map<String, String> getFloatMap() {
		return floatMap;
	}

	public void setFloatMap(Map<String, String> floatMap) {
		this.floatMap = floatMap;
	}

	public Map<String, String> getStrMap() {
		return strMap;
	}

	public void setStrMap(Map<String, String> strMap) {
		this.strMap = strMap;
	}

	public Map<String, String> getDateMap() {
		return dateMap;
	}

	public void setDateMap(Map<String, String> dateMap) {
		this.dateMap = dateMap;
	}

	public Map<String, String> getListMap() {
		return listMap;
	}

	public void setListMap(Map<String, String> listMap) {
		this.listMap = listMap;
	}

	public Map<String, String> getSetMap() {
		return setMap;
	}

	public void setSetMap(Map<String, String> setMap) {
		this.setMap = setMap;
	}

	public Map<String, String> getmMap() {
		return mMap;
	}

	public void setmMap(Map<String, String> mMap) {
		this.mMap = mMap;
	}

}
