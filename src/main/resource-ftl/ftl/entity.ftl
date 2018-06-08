<#if (entity.packageName)??>
package ${entity.packageName}
</#if>

<#if (entity.lList)?? && (entity.lList?size>0)>
import java.util.List
</#if>
<#if (entity.setList)?? && (entity.setList?size>0)>
import java.util.Set
</#if>
<#if (entity.mapList)?? && (entity.mapList?size>0)>
import java.util.Map
</#if>
<#if (entity.dateList)?? && (entity.dateList?size>0)>
import java.util.Date	
</#if>

<#if (entity.description)??>
/**
* ${entity.description}
*/
</#if>
public class ${entity.className?cap_first} {
	<#if (entity.integerList)?? && (entity.integerList?size>0) >
		<#list entity.integerList as intAttr>
		private Integer ${intAttr};
			
		public void set${intAttr?cap_first}(Integer ${intAttr}) {
			this.${intAttr} = ${intAttr};
		}
			
		public Integer get${intAttr?cap_first}() {
			return this.${intAttr};
		}
			
		</#list>
	</#if>
	<#if (entity.strList)?? && (entity.strList?size>0)>
		<#list entity.strList as strAttr>
		private String ${strAttr};
			
		public void set${strAttr?cap_first}(String ${strAttr}) {
			this.${strAttr} = ${strAttr};
		}
			
		public String get${strAttr?cap_first}() {
			return this.${strAttr};
		}
		
		</#list>
	</#if>
	<#if (entity.doubleList)?? && (entity.doubleList?size>0)>
		<#list entity.doubleList as doubleAttr>
		private Double ${doubleAttr};
			
		public void set${doubleAttr?cap_first}(Double ${doubleAttr}) {
			this.${doubleAttr} = ${doubleAttr};
		}
			
		public Double get${doubleAttr?cap_first}() {
			return this.${doubleAttr};
		}
		
		</#list>
	</#if>
	<#if (entity.floatList)?? && (entity.floatList?size>0)>
		<#list entity.floatList as floatAttr>
		private Float ${floatAttr};
			
		public void set${floatAttr?cap_first}(Float ${floatAttr}) {
			this.${floatAttr} = ${floatAttr};
		}
			
		public Float get${floatAttr?cap_first}() {
			return this.${floatAttr};
		}
		
		</#list>
	</#if>
	<#if (entity.dateList)?? && (entity.dateList?size>0)>
		<#list entity.dateList as dateAttr>
		private Date ${dateAttr};
			
		public void set${dateAttr?cap_first}(Date ${dateAttr}) {
			this.${dateAttr} = ${dateAttr};
		}
			
		public Date get${dateAttr?cap_first}() {
			return this.${dateAttr};
		}
		
		</#list>
	</#if>
	<#if (entity.lList)?? && (entity.lList?size>0)>
		<#list entity.lList as listAttr>
		private List ${listAttr};
			
		public void set${listAttr?cap_first}(List ${listAttr}) {
			this.${listAttr} = ${listAttr};
		}
			
		public List get${listAttr?cap_first}() {
			return this.${listAttr};
		}
		
		</#list>
	</#if>
	<#if (entity.setList)?? && (entity.setList?size>0)>
		<#list entity.setList as setAttr>
		private Set ${setAttr};
			
		public void set${setAttr?cap_first}(Set ${setAttr}) {
			this.${setAttr} = ${setAttr};
		}
			
		public Set get${setAttr?cap_first}() {
			return this.${setAttr};
		}
		
		</#list>
	</#if>
	<#if (entity.mapList)?? && (entity.mapList?size>0)>
		<#list entity.mapList as mapAttr>
		private Map ${mapAttr};
			
		public void set${mapAttr?cap_first}(Map ${mapAttr}) {
			this.${setAttr} = ${setAttr};
		}
			
		public Map get${mapAttr?cap_first}() {
			return this.${mapAttr};
		}
		
		</#list>
	</#if>
}