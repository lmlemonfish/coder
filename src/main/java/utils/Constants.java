package utils;

/**
 * 存放静态常量
 * @author ma
 *
 */
public class Constants {
	/**
	 * 界面相关的常量
	 */
	public static final int DEFAULT_X = 300; // 默认x横坐标
	public static final int DEFAULT_Y = 300; // 默认y纵坐标
	public static final int DEFAULT_WIDTH = 850; // 界面默认宽度
	public static final int DEFAULT_HEIGHT = 650; // 界面默认高度
	
	/**
	 * 是否使用spring的注解注入对象
	 */
	public static final String IS_USE_SPRING_ANNOTATION = "useSpringAnno"; 
	public static final int USE_SPRING_ANNOTATION = 1; // 使用
	public static final int NOT_USE_SPRING_ANNOTATION = 0; // 不使用
	
	/**
	 * 生成实体支持的类型
	 */
	public static final String INTEGER = "Integer";
	public static final String LONG = "Long";
	public static final String DOUBLE = "Double";
	public static final String FLOAT = "Float";
	public static final String STRING = "String";
	public static final String DATE = "Date";
	public static final String LIST = "List";
	public static final String SET = "Set";
	public static final String MAP = "Map";
	
	public static final String TEMPLATE = "template";
}
