<#if (template.service.packageName)??>
package ${template.service.packageName};
</#if>

import java.io.Serializable;
<#if (template.service.findAllMethod)??>
import java.util.List;
</#if>

/**
<#if (template.service.description)??>
* ${template.service.description}
</#if>
<#if (template.service.author)??>
* @author ${template.service.author}
</#if>
*/
public interface ${template.service.className?cap_first} {
	<#if (template.service.addMethod)??>
	public void ${template.service.addMethod}(${template.entity.className?cap_first} ${template.entity.className});
	</#if>
	
	<#if (template.service.updateMethod)??>
	public void ${template.service.updateMethod}(${template.entity.className?cap_first} ${template.entity.className});
	</#if>
	
	<#if (template.service.delMethod)??>
	public void ${template.service.delMethod}(Serializable id);
	</#if>
	
	<#if (template.service.findByIdMethod)??>
	public ${template.entity.className?cap_first} ${template.service.findByIdMethod}(Serializable id);
	</#if>
	
	<#if (template.service.delBatchMethod)??>
	public void ${template.service.delBatchMethod}(Serializable... id);
	</#if>
	
	<#if (template.service.updateBatchMethod)??>
	public void ${template.service.updateBatchMethod}(${template.entity.className?cap_first}... ${template.entity.className});
	</#if>
	
	<#if (template.service.findAllMethod)??>
	public List<${template.entity.className?cap_first}> ${template.service.findAllMethod}();
	</#if>
}