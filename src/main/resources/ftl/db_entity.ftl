<#include "lib.ftl">
package ${template.classInfo.packageName};

/**
 * @ClassName ${template.classInfo.className}.java
 * @Description ${template.classInfo.description} (对应数据库表: ${template.entity.tableName})
 * @Author ${template.classInfo.author}
 * @Date ${template.classInfo.date?string("yyyy/MM/dd HH:mm:ss")}
 * @Version 1.0
 **/
public class ${template.classInfo.className}Entity implements Serializable {

<#list template.entity.fieldList as item>
    private ${item.fieldType} ${item.fieldName}; <#if (item.note)?? && item.note != ''>//${item.note}</#if>
</#list>

<#--生成get/set方法-->
<#list template.entity.fieldList as item>
    public ${item.fieldType} get${item.fieldName?cap_first} () {
        return ${item.fieldName};
    }

    public ${item.fieldType} set${item.fieldName?cap_first} (${item.fieldType} ${item.fieldName}) {
        this.${item.fieldName} = ${item.fieldName};
    }

</#list>

<#--<@poundEl "haha"/>-->
<#--<@dollarEl "gaga" />-->
}