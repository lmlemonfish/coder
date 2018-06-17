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
* @Description ${template.service.description}
</#if>
<#if (template.service.author)??>
* @author ${template.service.author}
</#if>
* @Date ${template.classInfo.date?string("yyyy/MM/dd HH:mm:ss")}
* @Version 1.0
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
	public void ${template.service.addMethod}(${template.classInfo.className?cap_first} ${template.classInfo.className}) {
		<#if (template.dao.addMethod)??>
		${template.dao.className?uncap_first}.${template.dao.addMethod}(${template.classInfo.className});
		</#if>
	}
	</#if>

	<#if (template.service.addBatchMethod)??>
		@Override
		public void ${template.service.addBatchMethod}(${template.classInfo.className?cap_first} ${template.classInfo.className}) {
		<#if (template.dao.addBatchMethod)??>
			${template.dao.className?uncap_first}.${template.dao.addBatchMethod}(${template.classInfo.className});
		</#if>
		}
	</#if>
	
	<#if (template.service.updateMethod)??>
	@Override
	public void ${template.service.updateMethod}(${template.classInfo.className?cap_first} ${template.classInfo.className}) {
		<#if (template.dao.updateMethod)??>
		${template.dao.className?uncap_first}.${template.dao.updateMethod}(${template.classInfo.className});
		</#if>
	}
	</#if>
	
	<#if (template.service.delMethod)??>
	@Override
	public void ${template.service.delMethod}(Serializable id) {
		<#if (template.dao.delMethod)??>
		${template.dao.className?uncap_first}.${template.dao.delMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (template.service.findByIdMethod)??>
	@Override
	public ${template.classInfo.className?cap_first} ${template.service.findByIdMethod}(Serializable id) {
		<#if (template.dao.findByIdMethod)??>
		return ${template.dao.className?uncap_first}.${template.dao.findByIdMethod}(id);
		<#else>
		return null;
		</#if>
	}
	</#if>
	
	<#if (template.service.delBatchMethod)??>
	@Override
	public void ${template.service.delBatchMethod}(Serializable... id) {
		<#if (template.dao.delBatchMethod)??>
		${template.dao.className?uncap_first}.${template.dao.delBatchMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (template.service.updateBatchMethod)??>
	@Override
	public void ${template.service.updateBatchMethod}(${template.classInfo.className?cap_first}... ${template.classInfo.className}) {
		<#if (template.dao.updateBatchMethod)??>
		${template.dao.className?uncap_first}.${template.dao.updateBatchMethod}(${template.classInfo.className});
		</#if>
	}
	</#if>
	
	<#if (template.service.findAllMethod)??>
	@Override
	public List<${template.classInfo.className?cap_first}> ${template.service.findAllMethod}() {
		<#if (template.dao.findAllMethod)??>
		return ${template.dao.className?uncap_first}.${template.dao.findAllMethod}();
		<#else>
		return null;
		</#if>
	}
	</#if>
}