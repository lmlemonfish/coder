package bulider;

import entity.ServiceFileData;

/**
 * 生成文件建造者
 * @author ma
 *
 */
public abstract class FileBulider {

	/**
	 * 生成实体
	 */
	protected abstract void bulidEntity();
	
	/**
	 * 生成dao接口
	 */
	protected abstract void bulidDao();
	
	/**
	 * 生成service接口和service实现
	 */
	protected abstract void bulidService();
	
	/**
	 * 生成controllor
	 */
	protected abstract void bulidControllor();
	
	/**
	 * 构造的对象
	 * @return
	 */
	protected abstract ServiceFileData bulidServiceFile();
}
