<#if (template.dao.packageName)??>
package ${template.dao.packageName};
</#if>

import java.io.Serializable;
<#if (template.dao.findAllMethod)??>
import java.util.List;
</#if>

/**
<#if (template.dao.description)??>
* ${template.dao.description}
</#if>
<#if (template.dao.author)??>
* @author ${template.dao.author}
</#if>
*/
public interface ${template.dao.className?cap_first} {
	<#if (template.dao.addMethod)??>
	public void ${template.dao.addMethod}(${template.entity.className?cap_first} ${template.entity.className});
	</#if>
	
	<#if (template.dao.updateMethod)??>
	public void ${template.dao.updateMethod}(${template.entity.className?cap_first} ${template.entity.className});
	</#if>
	
	<#if (template.dao.delMethod)??>
	public void ${template.dao.delMethod}(Serializable id);
	</#if>
	
	<#if (template.dao.findByIdMethod)??>
	public ${template.entity.className?cap_first} ${template.dao.findByIdMethod}(Serializable id);
	</#if>
	
	<#if (template.dao.delBatchMethod)??>
	public void ${template.dao.delBatchMethod}(Serializable... id);
	</#if>
	
	<#if (template.dao.updateBatchMethod)??>
	public void ${template.dao.updateBatchMethod}(${template.entity.className?cap_first}... ${template.entity.className});
	</#if>
	
	<#if (template.dao.findAllMethod)??>
	public List<${template.entity.className?cap_first}> ${template.dao.findAllMethod}();
	</#if>
}