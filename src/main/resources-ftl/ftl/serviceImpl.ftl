<#if (serviceBasic.service.packageName)??>
package ${serviceBasic.service.packageName};
</#if>

import java.io.Serializable;
<#if (serviceBasic.service.findAllMethod)??>
import java.util.List;
</#if>
import org.springframework.stereotype.serviceBasic.service;
import org.springframework.transaction.annotation.Transactional;

/**
<#if (serviceBasic.service.description)??>
* ${serviceBasic.service.description}
</#if>
<#if (serviceBasic.service.author)??>
* @author ${serviceBasic.service.author}
</#if>
*/
@Service
@Transactional
public class ${serviceBasic.service.className?cap_first}Impl implements ${serviceBasic.service.className?cap_first} {
	<#if (serviceBasic.dao)??>
	<#if useSpringAnno==1>
	@Autowired
	private ${serviceBasic.dao.className?cap_first} ${serviceBasic.dao.className};
	<#else>  
    private ${serviceBasic.dao.className?cap_first} ${serviceBasic.dao.className} = new private ${serviceBasic.dao.className?cap_first} ${serviceBasic.dao.className}();;
	</#if>
	</#if>

	<#if (serviceBasic.service.addMethod)??>
	@Override
	public void ${serviceBasic.service.addMethod}(${serviceBasic.entity.className?cap_first} ${serviceBasic.entity.className}) {
		<#if (serviceBasic.dao.addMethod)??>
		${serviceBasic.dao.className}.${serviceBasic.dao.addMethod}(${serviceBasic.entity.className});
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.service.updateMethod)??>
	@Override
	public void ${serviceBasic.service.updateMethod}(${serviceBasic.entity.className?cap_first} ${serviceBasic.entity.className}) {
		<#if (serviceBasic.dao.updateMethod)??>
		${serviceBasic.dao.className}.${serviceBasic.dao.updateMethod}(${serviceBasic.entity.className});
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.service.delMethod)??>
	@Override
	public void ${serviceBasic.service.delMethod}(Serializable id) {
		<#if (serviceBasic.dao.delMethod)??>
		${serviceBasic.dao.className}.${serviceBasic.dao.delMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.service.findByIdMethod)??>
	@Override
	public ${serviceBasic.entity.className?cap_first} ${serviceBasic.service.findByIdMethod}(Serializable id) {
		<#if (serviceBasic.dao.findByIdMethod)??>
		return ${serviceBasic.dao.className}.${serviceBasic.dao.findByIdMethod}(id);
		<#else>
		return null;
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.service.delBatchMethod)??>
	@Override
	public void ${serviceBasic.service.delBatchMethod}(Serializable... id) {
		<#if (serviceBasic.dao.delBatchMethod)??>
		${serviceBasic.dao.className}.${serviceBasic.dao.delBatchMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.service.updateBatchMethod)??>
	@Override
	public void ${serviceBasic.service.updateBatchMethod}(${serviceBasic.entity.className?cap_first}... ${serviceBasic.entity.className}) {
		<#if (serviceBasic.dao.updateBatchMethod)??>
		${serviceBasic.dao.className}.${serviceBasic.dao.updateBatchMethod}(${serviceBasic.entity.className});
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.service.findAllMethod)??>
	@Override
	public List<${serviceBasic.entity.className?cap_first}> ${serviceBasic.service.findAllMethod}() {
		<#if (serviceBasic.dao.findAllMethod)??>
		return ${serviceBasic.dao.className}.${serviceBasic.dao.findAllMethod}();
		<#else>
		return null;
		</#if>
	}
	</#if>
}