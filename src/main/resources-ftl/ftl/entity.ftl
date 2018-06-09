<#if (serviceBasic.entity.packageName)??>
package ${serviceBasic.entity.packageName};
</#if>

<#if (serviceBasic.entity.listMap)?? && (serviceBasic.entity.listMap?size>0)>
import java.util.List;
</#if>
<#if (serviceBasic.entity.setMap)?? && (serviceBasic.entity.setMap?size>0)>
import java.util.Set;
</#if>
<#if (serviceBasic.entity.mMap)?? && (serviceBasic.entity.mMap?size>0)>
import java.util.Map;
</#if>
<#if (serviceBasic.entity.dateMap)?? && (serviceBasic.entity.dateMap?size>0)>
import java.util.Date;
</#if>

/**
<#if (serviceBasic.entity.description)??>
* ${serviceBasic.entity.description}
</#if>
<#if (serviceBasic.entity.author)??>
* @author ${serviceBasic.entity.author}
</#if>
*/
public class ${serviceBasic.entity.className?cap_first} {
<#if (serviceBasic.entity.integerMap)?? && (serviceBasic.entity.integerMap?size>0) >
	<#list serviceBasic.entity.integerMap?keys as intAttr>
	private Integer ${intAttr}; // ${serviceBasic.entity.integerMap[intAttr]}
			
	public void set${intAttr?cap_first}(Integer ${intAttr}) {
		this.${intAttr} = ${intAttr};
	}
			
	public Integer get${intAttr?cap_first}() {
		return this.${intAttr};
	}
			
	</#list>
</#if>
<#if (serviceBasic.entity.strMap)?? && (serviceBasic.entity.strMap?size>0)>
	<#list serviceBasic.entity.strMap?keys as strAttr>
	private String ${strAttr}; // ${serviceBasic.entity.strMap[strAttr]}
			
	public void set${strAttr?cap_first}(String ${strAttr}) {
		this.${strAttr} = ${strAttr};
	}
			
	public String get${strAttr?cap_first}() {
		return this.${strAttr};
	}
		
	</#list>
</#if>
<#if (serviceBasic.entity.doubleMap)?? && (serviceBasic.entity.doubleMap?size>0)>
	<#list serviceBasic.entity.doubleMap?keys as doubleAttr>
	private Double ${doubleAttr}; // ${serviceBasic.entity.strMap[doubleAttr]}
			
	public void set${doubleAttr?cap_first}(Double ${doubleAttr}) {
		this.${doubleAttr} = ${doubleAttr};
	}
			
	public Double get${doubleAttr?cap_first}() {
		return this.${doubleAttr};
	}
		
	</#list>
</#if>
<#if (serviceBasic.entity.floatMap)?? && (serviceBasic.entity.floatMap?size>0)>
	<#list serviceBasic.entity.floatMap?keys as floatAttr>
	private Float ${floatAttr}; // ${serviceBasic.entity.floatMap[floatAttr]}
			
	public void set${floatAttr?cap_first}(Float ${floatAttr}) {
		this.${floatAttr} = ${floatAttr};
	}
			
	public Float get${floatAttr?cap_first}() {
		return this.${floatAttr};
	}
		
	</#list>
</#if>
<#if (serviceBasic.entity.dateMap)?? && (serviceBasic.entity.dateMap?size>0)>
	<#list serviceBasic.entity.dateMap?keys as dateAttr>
	private Date ${dateAttr}; // ${serviceBasic.entity.dateMap[dateAttr]}
			
	public void set${dateAttr?cap_first}(Date ${dateAttr}) {
		this.${dateAttr} = ${dateAttr};
	}
			
	public Date get${dateAttr?cap_first}() {
		return this.${dateAttr};
	}
		
	</#list>
</#if>
<#if (serviceBasic.entity.listMap)?? && (serviceBasic.entity.listMap?size>0)>
	<#list serviceBasic.entity.listMap?keys as listAttr>
	private List ${listAttr}; // ${serviceBasic.entity.listMap[listAttr]}
			
	public void set${listAttr?cap_first}(List ${listAttr}) {
		this.${listAttr} = ${listAttr};
	}
			
	public List get${listAttr?cap_first}() {
		return this.${listAttr};
	}
		
	</#list>
</#if>
<#if (serviceBasic.entity.setMap)?? && (serviceBasic.entity.setMap?size>0)>
	<#list serviceBasic.entity.setMap?keys as setAttr>
	private Set ${setAttr}; // ${serviceBasic.entity.setMap[setAttr]}
			
	public void set${setAttr?cap_first}(Set ${setAttr}) {
		this.${setAttr} = ${setAttr};
	}
			
	public Set get${setAttr?cap_first}() {
		return this.${setAttr};
	}
		
	</#list>
</#if>
<#if (serviceBasic.entity.mMap)?? && (serviceBasic.entity.mMap?size>0)>
	<#list serviceBasic.entity.mMap?keys as mapAttr>
	private Map ${mapAttr}; // ${serviceBasic.entity.mMap[mapAttr]}
			
	public void set${mapAttr?cap_first}(Map ${mapAttr}) {
		this.${setAttr} = ${setAttr};
	}
			
	public Map get${mapAttr?cap_first}() {
		return this.${mapAttr};
	}
		
	</#list>
</#if>
}