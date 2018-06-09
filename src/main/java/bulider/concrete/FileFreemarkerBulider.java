package bulider.concrete;

import bulider.FileBulider;
import entity.ServiceFileData;

/**
 * 生成的文件具体构造者
 * 通过freemarker提供的模板方式生成
 * @author ma
 *
 */
public class FileFreemarkerBulider extends FileBulider {
	private ServiceFileData serviceFile = new ServiceFileData();

	@Override
	protected void bulidEntity() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void bulidDao() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void bulidService() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void bulidControllor() {
		// TODO Auto-generated method stub

	}

	@Override
	protected ServiceFileData bulidServiceFile() {
		return serviceFile;
	}

}
