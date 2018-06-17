<#if (template.entity.packageName)??>
package ${template.entity.packageName};
</#if>

<#if (template.entity.listMap)?? && (template.entity.listMap?size>0)>
import java.util.List;
</#if>
<#if (template.entity.setMap)?? && (template.entity.setMap?size>0)>
import java.util.Set;
</#if>
<#if (template.entity.mMap)?? && (template.entity.mMap?size>0)>
import java.util.Map;
</#if>
<#if (template.entity.dateMap)?? && (template.entity.dateMap?size>0)>
import java.util.Date;
</#if>

/**
<#if (template.entity.description)??>
* ${template.entity.description}
</#if>
<#if (template.entity.author)??>
* @author ${template.entity.author}
</#if>
*/
public class ${template.entity.className?cap_first} {
<#if (template.entity.integerMap)?? && (template.entity.integerMap?size>0) >
	<#list template.entity.integerMap?keys as intAttr>
	private Integer ${intAttr}; // ${template.entity.integerMap[intAttr]}
			
	public void set${intAttr?cap_first}(Integer ${intAttr}) {
		this.${intAttr} = ${intAttr};
	}
			
	public Integer get${intAttr?cap_first}() {
		return this.${intAttr};
	}
			
	</#list>
</#if>
<#if (template.entity.strMap)?? && (template.entity.strMap?size>0)>
	<#list template.entity.strMap?keys as strAttr>
	private String ${strAttr}; // ${template.entity.strMap[strAttr]}
			
	public void set${strAttr?cap_first}(String ${strAttr}) {
		this.${strAttr} = ${strAttr};
	}
			
	public String get${strAttr?cap_first}() {
		return this.${strAttr};
	}
		
	</#list>
</#if>
<#if (template.entity.doubleMap)?? && (template.entity.doubleMap?size>0)>
	<#list template.entity.doubleMap?keys as doubleAttr>
	private Double ${doubleAttr}; // ${template.entity.strMap[doubleAttr]}
			
	public void set${doubleAttr?cap_first}(Double ${doubleAttr}) {
		this.${doubleAttr} = ${doubleAttr};
	}
			
	public Double get${doubleAttr?cap_first}() {
		return this.${doubleAttr};
	}
		
	</#list>
</#if>
<#if (template.entity.floatMap)?? && (template.entity.floatMap?size>0)>
	<#list template.entity.floatMap?keys as floatAttr>
	private Float ${floatAttr}; // ${template.entity.floatMap[floatAttr]}
			
	public void set${floatAttr?cap_first}(Float ${floatAttr}) {
		this.${floatAttr} = ${floatAttr};
	}
			
	public Float get${floatAttr?cap_first}() {
		return this.${floatAttr};
	}
		
	</#list>
</#if>
<#if (template.entity.dateMap)?? && (template.entity.dateMap?size>0)>
	<#list template.entity.dateMap?keys as dateAttr>
	private Date ${dateAttr}; // ${template.entity.dateMap[dateAttr]}
			
	public void set${dateAttr?cap_first}(Date ${dateAttr}) {
		this.${dateAttr} = ${dateAttr};
	}
			
	public Date get${dateAttr?cap_first}() {
		return this.${dateAttr};
	}
		
	</#list>
</#if>
<#if (template.entity.listMap)?? && (template.entity.listMap?size>0)>
	<#list template.entity.listMap?keys as listAttr>
	private List ${listAttr}; // ${template.entity.listMap[listAttr]}
			
	public void set${listAttr?cap_first}(List ${listAttr}) {
		this.${listAttr} = ${listAttr};
	}
			
	public List get${listAttr?cap_first}() {
		return this.${listAttr};
	}
		
	</#list>
</#if>
<#if (template.entity.setMap)?? && (template.entity.setMap?size>0)>
	<#list template.entity.setMap?keys as setAttr>
	private Set ${setAttr}; // ${template.entity.setMap[setAttr]}
			
	public void set${setAttr?cap_first}(Set ${setAttr}) {
		this.${setAttr} = ${setAttr};
	}
			
	public Set get${setAttr?cap_first}() {
		return this.${setAttr};
	}
		
	</#list>
</#if>
<#if (template.entity.mMap)?? && (template.entity.mMap?size>0)>
	<#list template.entity.mMap?keys as mapAttr>
	private Map ${mapAttr}; // ${template.entity.mMap[mapAttr]}
			
	public void set${mapAttr?cap_first}(Map ${mapAttr}) {
		this.${setAttr} = ${setAttr};
	}
			
	public Map get${mapAttr?cap_first}() {
		return this.${mapAttr};
	}
		
	</#list>
</#if>
}