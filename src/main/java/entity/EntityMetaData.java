package entity;

import java.util.List;

/**
 * 封装生成实体的信息
 * 
 * @author ma
 *
 */
public class EntityMetaData {
	private String packageName;
	private String className;
	private String description;
	private List<String> integerList;
	private List<String> longList;
	private List<String> doubleList;
	private List<String> floatList;
	private List<String> strList;
	private List<String> dateList;
	private List<String> lList;
	private List<String> setList;
	private List<String> mapList;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getIntegerList() {
		return integerList;
	}

	public void setIntegerList(List<String> integerList) {
		this.integerList = integerList;
	}

	public List<String> getLongList() {
		return longList;
	}

	public void setLongList(List<String> longList) {
		this.longList = longList;
	}

	public List<String> getDoubleList() {
		return doubleList;
	}

	public void setDoubleList(List<String> doubleList) {
		this.doubleList = doubleList;
	}

	public List<String> getFloatList() {
		return floatList;
	}

	public void setFloatList(List<String> floatList) {
		this.floatList = floatList;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public List<String> getDateList() {
		return dateList;
	}

	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}

	public List<String> getlList() {
		return lList;
	}

	public void setlList(List<String> lList) {
		this.lList = lList;
	}

	public List<String> getSetList() {
		return setList;
	}

	public void setSetList(List<String> setList) {
		this.setList = setList;
	}

	public List<String> getMapList() {
		return mapList;
	}

	public void setMapList(List<String> mapList) {
		this.mapList = mapList;
	}

}
