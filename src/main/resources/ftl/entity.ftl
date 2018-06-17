<#if (template.classInfo.packageName)??>
package ${template.classInfo.packageName};
</#if>

<#if (template.classInfo.listMap)?? && (template.classInfo.listMap?size>0)>
import java.util.List;
</#if>
<#if (template.classInfo.setMap)?? && (template.classInfo.setMap?size>0)>
import java.util.Set;
</#if>
<#if (template.classInfo.mMap)?? && (template.classInfo.mMap?size>0)>
import java.util.Map;
</#if>
<#if (template.classInfo.dateMap)?? && (template.classInfo.dateMap?size>0)>
import java.util.Date;
</#if>

/**
<#if (template.classInfo.description)??>
* ${template.classInfo.description}
</#if>
<#if (template.classInfo.author)??>
* @author ${template.classInfo.author}
</#if>
*/
public class ${template.classInfo.className?cap_first} {
<#if (template.classInfo.integerMap)?? && (template.classInfo.integerMap?size>0) >
	<#list template.classInfo.integerMap?keys as intAttr>
	private Integer ${intAttr}; // ${template.classInfo.integerMap[intAttr]}
			
	public void set${intAttr?cap_first}(Integer ${intAttr}) {
		this.${intAttr} = ${intAttr};
	}
			
	public Integer get${intAttr?cap_first}() {
		return this.${intAttr};
	}
			
	</#list>
</#if>
<#if (template.classInfo.strMap)?? && (template.classInfo.strMap?size>0)>
	<#list template.classInfo.strMap?keys as strAttr>
	private String ${strAttr}; // ${template.classInfo.strMap[strAttr]}
			
	public void set${strAttr?cap_first}(String ${strAttr}) {
		this.${strAttr} = ${strAttr};
	}
			
	public String get${strAttr?cap_first}() {
		return this.${strAttr};
	}
		
	</#list>
</#if>
<#if (template.classInfo.doubleMap)?? && (template.classInfo.doubleMap?size>0)>
	<#list template.classInfo.doubleMap?keys as doubleAttr>
	private Double ${doubleAttr}; // ${template.classInfo.strMap[doubleAttr]}
			
	public void set${doubleAttr?cap_first}(Double ${doubleAttr}) {
		this.${doubleAttr} = ${doubleAttr};
	}
			
	public Double get${doubleAttr?cap_first}() {
		return this.${doubleAttr};
	}
		
	</#list>
</#if>
<#if (template.classInfo.floatMap)?? && (template.classInfo.floatMap?size>0)>
	<#list template.classInfo.floatMap?keys as floatAttr>
	private Float ${floatAttr}; // ${template.classInfo.floatMap[floatAttr]}
			
	public void set${floatAttr?cap_first}(Float ${floatAttr}) {
		this.${floatAttr} = ${floatAttr};
	}
			
	public Float get${floatAttr?cap_first}() {
		return this.${floatAttr};
	}
		
	</#list>
</#if>
<#if (template.classInfo.dateMap)?? && (template.classInfo.dateMap?size>0)>
	<#list template.classInfo.dateMap?keys as dateAttr>
	private Date ${dateAttr}; // ${template.classInfo.dateMap[dateAttr]}
			
	public void set${dateAttr?cap_first}(Date ${dateAttr}) {
		this.${dateAttr} = ${dateAttr};
	}
			
	public Date get${dateAttr?cap_first}() {
		return this.${dateAttr};
	}
		
	</#list>
</#if>
<#if (template.classInfo.listMap)?? && (template.classInfo.listMap?size>0)>
	<#list template.classInfo.listMap?keys as listAttr>
	private List ${listAttr}; // ${template.classInfo.listMap[listAttr]}
			
	public void set${listAttr?cap_first}(List ${listAttr}) {
		this.${listAttr} = ${listAttr};
	}
			
	public List get${listAttr?cap_first}() {
		return this.${listAttr};
	}
		
	</#list>
</#if>
<#if (template.classInfo.setMap)?? && (template.classInfo.setMap?size>0)>
	<#list template.classInfo.setMap?keys as setAttr>
	private Set ${setAttr}; // ${template.classInfo.setMap[setAttr]}
			
	public void set${setAttr?cap_first}(Set ${setAttr}) {
		this.${setAttr} = ${setAttr};
	}
			
	public Set get${setAttr?cap_first}() {
		return this.${setAttr};
	}
		
	</#list>
</#if>
<#if (template.classInfo.mMap)?? && (template.classInfo.mMap?size>0)>
	<#list template.classInfo.mMap?keys as mapAttr>
	private Map ${mapAttr}; // ${template.classInfo.mMap[mapAttr]}
			
	public void set${mapAttr?cap_first}(Map ${mapAttr}) {
		this.${setAttr} = ${setAttr};
	}
			
	public Map get${mapAttr?cap_first}() {
		return this.${mapAttr};
	}
		
	</#list>
</#if>
}