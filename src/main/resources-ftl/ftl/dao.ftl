<#if (serviceBasic.dao.packageName)??>
package ${serviceBasic.dao.packageName};
</#if>

import java.io.Serializable;
<#if (serviceBasic.dao.findAllMethod)??>
import java.util.List;
</#if>

/**
<#if (serviceBasic.dao.description)??>
* ${serviceBasic.dao.description}
</#if>
<#if (serviceBasic.dao.author)??>
* @author ${serviceBasic.dao.author}
</#if>
*/
public interface ${serviceBasic.dao.className?cap_first} {
	<#if (serviceBasic.dao.addMethod)??>
	public void ${serviceBasic.dao.addMethod}(${serviceBasic.entity.className?cap_first} ${serviceBasic.entity.className});
	</#if>
	
	<#if (serviceBasic.dao.updateMethod)??>
	public void ${serviceBasic.dao.updateMethod}(${serviceBasic.entity.className?cap_first} ${serviceBasic.entity.className});
	</#if>
	
	<#if (serviceBasic.dao.delMethod)??>
	public void ${serviceBasic.dao.delMethod}(Serializable id);
	</#if>
	
	<#if (serviceBasic.dao.findByIdMethod)??>
	public ${serviceBasic.entity.className?cap_first} ${serviceBasic.dao.findByIdMethod}(Serializable id);
	</#if>
	
	<#if (serviceBasic.dao.delBatchMethod)??>
	public void ${serviceBasic.dao.delBatchMethod}(Serializable... id);
	</#if>
	
	<#if (serviceBasic.dao.updateBatchMethod)??>
	public void ${serviceBasic.dao.updateBatchMethod}(${serviceBasic.entity.className?cap_first}... ${serviceBasic.entity.className});
	</#if>
	
	<#if (serviceBasic.dao.findAllMethod)??>
	public List<${serviceBasic.entity.className?cap_first}> ${serviceBasic.dao.findAllMethod}();
	</#if>
}