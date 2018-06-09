package entity.basic;

/**
 * 生成业务处理类中所包含的信息(controllor、service、dao)
 * 主要是封装生成的方法名
 * @author ma
 *
 */
public class ServiceBasicData extends ClassBasicData {
	private String addMethod; // 新增方法方法名
	private String updateMethod; // 修改操作方法名
	private String delMethod; // 删除操作方法名
	private String findByIdMethod; // 根据ID查询方法名
	private String delBatchMethod; // 批量删除方法名
	private String updateBatchMethod; // 批量删除方法名
	private String findAllMethod; // 查询列表方法名

	public String getAddMethod() {
		return addMethod;
	}

	public void setAddMethod(String addMethod) {
		this.addMethod = addMethod;
	}

	public String getUpdateMethod() {
		return updateMethod;
	}

	public void setUpdateMethod(String updateMethod) {
		this.updateMethod = updateMethod;
	}

	public String getDelMethod() {
		return delMethod;
	}

	public void setDelMethod(String delMethod) {
		this.delMethod = delMethod;
	}

	public String getFindByIdMethod() {
		return findByIdMethod;
	}

	public void setFindByIdMethod(String findByIdMethod) {
		this.findByIdMethod = findByIdMethod;
	}

	public String getDelBatchMethod() {
		return delBatchMethod;
	}

	public void setDelBatchMethod(String delBatchMethod) {
		this.delBatchMethod = delBatchMethod;
	}

	public String getUpdateBatchMethod() {
		return updateBatchMethod;
	}

	public void setUpdateBatchMethod(String updateBatchMethod) {
		this.updateBatchMethod = updateBatchMethod;
	}

	public String getFindAllMethod() {
		return findAllMethod;
	}

	public void setFindAllMethod(String findAllMethod) {
		this.findAllMethod = findAllMethod;
	}

}
