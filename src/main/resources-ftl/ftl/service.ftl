<#if (serviceBasic.service.packageName)??>
package ${serviceBasic.service.packageName};
</#if>

import java.io.Serializable;
<#if (serviceBasic.service.findAllMethod)??>
import java.util.List;
</#if>

/**
<#if (serviceBasic.service.description)??>
* ${serviceBasic.service.description}
</#if>
<#if (serviceBasic.service.author)??>
* @author ${serviceBasic.service.author}
</#if>
*/
public interface ${serviceBasic.service.className?cap_first} {
	<#if (serviceBasic.service.addMethod)??>
	public void ${serviceBasic.service.addMethod}(${serviceBasic.entity.className?cap_first} ${serviceBasic.entity.className});
	</#if>
	
	<#if (serviceBasic.service.updateMethod)??>
	public void ${serviceBasic.service.updateMethod}(${serviceBasic.entity.className?cap_first} ${serviceBasic.entity.className});
	</#if>
	
	<#if (serviceBasic.service.delMethod)??>
	public void ${serviceBasic.service.delMethod}(Serializable id);
	</#if>
	
	<#if (serviceBasic.service.findByIdMethod)??>
	public ${serviceBasic.entity.className?cap_first} ${serviceBasic.service.findByIdMethod}(Serializable id);
	</#if>
	
	<#if (serviceBasic.service.delBatchMethod)??>
	public void ${serviceBasic.service.delBatchMethod}(Serializable... id);
	</#if>
	
	<#if (serviceBasic.service.updateBatchMethod)??>
	public void ${serviceBasic.service.updateBatchMethod}(${serviceBasic.entity.className?cap_first}... ${serviceBasic.entity.className});
	</#if>
	
	<#if (serviceBasic.service.findAllMethod)??>
	public List<${serviceBasic.entity.className?cap_first}> ${serviceBasic.service.findAllMethod}();
	</#if>
}