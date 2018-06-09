package entity.basic;

/**
 * 生成类的类中所包含的基本信息
 * 
 * @author ma
 *
 */
public class ClassBasicData {
	private String packageName; // 包名
	private String className;   // 类名
	private String description; // 类功能注释
	private String author; // 作者

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

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

}
