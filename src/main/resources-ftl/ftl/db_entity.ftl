<#include "lib.ftl">
package ${classInfo.packageName};

/**
 * @ClassName ${classInfo.className}.java
 * @Description ${entity.note} (对应数据库表: ${entity.tableName})
 * @Author ${classInfo.author}
 * @Date ${classInfo.date?string("yyyy/MM/dd HH:mm:ss")}
 * @Version 1.0
 **/
public class ${classInfo.className}Entity implements Serializable {

<#list entity.fieldList as item>
    private ${item.fieldType} ${item.fieldName}; <#if (item.note)?? && item.note != ''>//${item.note}</#if>
</#list>

<#--生成get/set方法-->
<#list entity.fieldList as item>
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