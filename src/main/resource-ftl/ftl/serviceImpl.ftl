<#if (template.service.packageName)??>
package ${template.service.packageName};
</#if>

import java.io.Serializable;
<#if (template.service.findAllMethod)??>
import java.util.List;
</#if>
import org.springframework.stereotype.template.service;
import org.springframework.transaction.annotation.Transactional;

/**
<#if (template.service.description)??>
* ${template.service.description}
</#if>
<#if (template.service.author)??>
* @author ${template.service.author}
</#if>
*/
@Service
@Transactional
public class ${template.service.className?cap_first}Impl implements ${template.service.className?cap_first} {
	<#if (template.dao)??>
	<#if useSpringAnno==1>
	@Autowired
	private ${template.dao.className?cap_first} ${template.dao.className};
	<#else>  
    private ${template.dao.className?cap_first} ${template.dao.className} = new private ${template.dao.className?cap_first} ${template.dao.className}();;
	</#if>
	</#if>

	<#if (template.service.addMethod)??>
	@Override
	public void ${template.service.addMethod}(${template.entity.className?cap_first} ${template.entity.className}) {
		<#if (template.dao.addMethod)??>
		${template.dao.className}.${template.dao.addMethod}(${template.entity.className});
		</#if>
	}
	</#if>
	
	<#if (template.service.updateMethod)??>
	@Override
	public void ${template.service.updateMethod}(${template.entity.className?cap_first} ${template.entity.className}) {
		<#if (template.dao.updateMethod)??>
		${template.dao.className}.${template.dao.updateMethod}(${template.entity.className});
		</#if>
	}
	</#if>
	
	<#if (template.service.delMethod)??>
	@Override
	public void ${template.service.delMethod}(Serializable id) {
		<#if (template.dao.delMethod)??>
		${template.dao.className}.${template.dao.delMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (template.service.findByIdMethod)??>
	@Override
	public ${template.entity.className?cap_first} ${template.service.findByIdMethod}(Serializable id) {
		<#if (template.dao.findByIdMethod)??>
		return ${template.dao.className}.${template.dao.findByIdMethod}(id);
		<#else>
		return null;
		</#if>
	}
	</#if>
	
	<#if (template.service.delBatchMethod)??>
	@Override
	public void ${template.service.delBatchMethod}(Serializable... id) {
		<#if (template.dao.delBatchMethod)??>
		${template.dao.className}.${template.dao.delBatchMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (template.service.updateBatchMethod)??>
	@Override
	public void ${template.service.updateBatchMethod}(${template.entity.className?cap_first}... ${template.entity.className}) {
		<#if (template.dao.updateBatchMethod)??>
		${template.dao.className}.${template.dao.updateBatchMethod}(${template.entity.className});
		</#if>
	}
	</#if>
	
	<#if (template.service.findAllMethod)??>
	@Override
	public List<${template.entity.className?cap_first}> ${template.service.findAllMethod}() {
		<#if (template.dao.findAllMethod)??>
		return ${template.dao.className}.${template.dao.findAllMethod}();
		<#else>
		return null;
		</#if>
	}
	</#if>
}