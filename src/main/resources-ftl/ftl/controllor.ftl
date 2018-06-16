<#if (template.controllor.packageName)??>
package ${template.controllor.packageName};
</#if>

import org.springframework.stereotype.Controller;
<#if useSpringAnno==1>
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Serializable;
<#if (template.controllor.findAllMethod)??>
import java.util.List;
</#if>

/**
<#if (template.controllor.description)??>
* @Description ${template.controllor.description}
</#if>
<#if (template.controllor.author)??>
* @author ${template.controllor.author}
* @Date ${template.classInfo.date?string("yyyy/MM/dd HH:mm:ss")}
* @Version 1.0
</#if>
*/
@Controllor
public class ${template.controllor.className?cap_first} {
	<#if (template.service)??>
	<#if useSpringAnno==1>
	@Autowired
	private ${template.service.className?cap_first} ${template.service.className};
	<#else>  
    private ${template.service.className?cap_first} ${template.service.className} = new private ${template.service.className?cap_first} ${template.service.className}();;
	</#if>
	</#if>
	
	<#if (template.controllor.addMethod)??>
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void ${template.controllor.addMethod}(${template.classInfo.className?cap_first} ${template.classInfo.className}) {
		<#if (template.service.addMethod)??>
		${template.service.className?uncap_first}.${template.service.addMethod}(${template.classInfo.className});
		</#if>
	}
	</#if>

	<#if (template.controllor.addBatchMethod)??>
		@RequestMapping(value="/save", method=RequestMethod.POST)
		public void ${template.controllor.addBatchMethod}(${template.classInfo.className?cap_first} ${template.classInfo.className}) {
		<#if (template.service.addBatchMethod)??>
			${template.service.className?uncap_first}.${template.service.addBatchMethod}(${template.classInfo.className});
		</#if>
		}
	</#if>
	
	<#if (template.controllor.updateMethod)??>
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void ${template.controllor.updateMethod}(${template.classInfo.className?cap_first} ${template.classInfo.className}) {
		<#if (template.service.updateMethod)??>
		${template.service.className?uncap_first}.${template.service.updateMethod}(${template.classInfo.className});
		</#if>
	}
	</#if>
	
	<#if (template.controllor.delMethod)??>
	@RequestMapping("/delById")
	@ResponseBody
	public void ${template.controllor.delMethod}(Serializable id) {
		<#if (template.service.delMethod)??>
		${template.service.className?uncap_first}.${template.service.delMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (template.controllor.findByIdMethod)??>
	@RequestMapping("/load")
	public ${template.classInfo.className} ${template.controllor.findByIdMethod}(Serializable id) {
		<#if (template.service.findByIdMethod)??>
		return ${template.service.className?uncap_first}.${template.service.findByIdMethod}(id);
		<#else>
		return null;
		</#if>
	}
	</#if>
	
	<#if (template.controllor.delBatchMethod)??>
	@RequestMapping("/delBatch")
	public void ${template.controllor.delBatchMethod}(Serializable... id) {
		<#if (template.service.delBatchMethod)??>
		${template.service.className?uncap_first}.${template.service.delBatchMethod}(id);
		</#if>
	}
	</#if>
	
	<#if (template.controllor.updateBatchMethod)??>
	@RequestMapping("/delBatch")
	@ResponseBody
	public void ${template.controllor.updateBatchMethod}(${template.classInfo.className?cap_first}... ${template.classInfo.className}) {
		<#if (template.service.updateBatchMethod)??>
		${template.service.className?uncap_first}.${template.service.updateBatchMethod}(${template.classInfo.className});
		</#if>
	}
	</#if>
	
	<#if (template.controllor.findAllMethod)??>
	@RequestMapping("/list")
	@ResponseBody
	public List<${template.classInfo.className}> ${template.controllor.findAllMethod}() {
		<#if (template.service.findAllMethod)??>
		return ${template.service.className?uncap_first}.${template.service.findAllMethod}();
		<#else>
		return null;
		</#if>
	}
	</#if>
	
}
