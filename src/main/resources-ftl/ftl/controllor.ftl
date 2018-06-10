<#if (serviceBasic.controllor.packageName)??>
package ${serviceBasic.controllor.packageName};
</#if>

import org.springframework.stereotype.Controller;
<#if useSpringAnno==1>
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Serializable;
<#if (serviceBasic.controllor.findAllMethod)??>
import java.util.List;
</#if>

/**
<#if (serviceBasic.controllor.description)??>
* ${serviceBasic.controllor.description}
</#if>
<#if (serviceBasic.controllor.author)??>
* @author ${serviceBasic.controllor.author}
</#if>
*/
@Controllor
public class ${serviceBasic.controllor.className?cap_first} {
	<#if (serviceBasic.service)??>
	<#if useSpringAnno==1>
	@Autowired
	private ${serviceBasic.service.className?cap_first} ${serviceBasic.service.className};
	<#else>  
    private ${serviceBasic.service.className?cap_first} ${serviceBasic.service.className} = new private ${serviceBasic.service.className?cap_first} ${serviceBasic.service.className}();;
	</#if>
	</#if>
	
	<#if (serviceBasic.controllor.addMethod)??>
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void ${serviceBasic.controllor.addMethod}(${serviceBasic.entity.className?cap_first} ${serviceBasic.entity.className}) {
		<#if (serviceBasic.service.addMethod)??>
		${serviceBasic.service.className}.${serviceBasic.service.addMethod}(${serviceBasic.entity.className});
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.controllor.updateMethod)??>
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void ${serviceBasic.controllor.updateMethod}(${serviceBasic.entity.className?cap_first} ${serviceBasic.entity.className}) {
		<#if (serviceBasic.service.updateMethod)??>
		${serviceBasic.service.className}.${serviceBasic.service.updateMethod}(${serviceBasic.entity.className});
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.controllor.delMethod)??>
	@RequestMapping("/delById")
	@ResponseBody
	public void ${serviceBasic.controllor.delMethod}(Serializable id) {
		<#if (serviceBasic.service.delMethod)??>
		${serviceBasic.service.className}.${serviceBasic.service.delMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.controllor.findByIdMethod)??>
	@RequestMapping("/load")
	public ${serviceBasic.entity.className} ${serviceBasic.controllor.findByIdMethod}(Serializable id) {
		<#if (serviceBasic.service.findByIdMethod)??>
		return ${serviceBasic.service.className}.${serviceBasic.service.findByIdMethod}(id);
		<#else>
		return null;
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.controllor.delBatchMethod)??>
	@RequestMapping("/delBatch")
	public void ${serviceBasic.controllor.delBatchMethod}(Serializable... id) {
		<#if (serviceBasic.service.delBatchMethod)??>
		${serviceBasic.service.className}.${serviceBasic.service.delBatchMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.controllor.updateBatchMethod)??>
	@RequestMapping("/delBatch")
	@ResponseBody
	public void ${serviceBasic.controllor.updateBatchMethod}(${serviceBasic.entity.className?cap_first}... ${serviceBasic.entity.className}) {
		<#if (serviceBasic.service.updateBatchMethod)??>
		${serviceBasic.service.className}.${serviceBasic.service.updateBatchMethod}(${serviceBasic.entity.className});
		</#if>
	}
	</#if>
	
	<#if (serviceBasic.controllor.findAllMethod)??>
	@RequestMapping("/list")
	@ResponseBody
	public List<${serviceBasic.entity.className}> ${serviceBasic.controllor.findAllMethod}() {
		<#if (serviceBasic.service.findAllMethod)??>
		return ${serviceBasic.service.className}.${serviceBasic.service.findAllMethod}();
		<#else>
		return null;
		</#if>
	}
	</#if>
	
}
