<#--定义可配置的一些属性-->
<#--软删除标识符-->
<#assign table_sys_flag="status" />
<#assign entity_sys_flag="" />
<#assign table_insert_value="" />
<#assign baseValueSql=""/>

<#list template.tableInfo.fieldList as item>
    <#assign temp = "${template.entity.fieldList[item_index].fieldName}" />
    <#if item.fieldName == table_sys_flag>
        <#assign entity_sys_flag = "${temp}"/>
    </#if>
</#list>

<#list template.entity.fieldList as item>
    <#assign temp = ""/>
    <#list template.tableInfo.fieldList as item>
        <#if item_has_next>
            <#assign convert><@poundEl "${item.fieldName}"/>,</#assign>
            <#assign temp = temp+convert/>
        <#else>
            <#assign convert><@poundEl "${item.fieldName}"/></#assign>
            <#assign temp = temp+convert/>
        </#if>
    </#list>
    <#assign baseValueSql = temp/>
</#list>

<#list template.tableInfo.fieldList as item>
    <#if item_has_next>
        <#assign table_insert_value = table_insert_value + item.fieldName + ','/>
    <#else>
        <#assign table_insert_value = table_insert_value + item.fieldName/>
    </#if>
</#list>

<#assign updateBatchTemplate>
UPDATE ${table_name}
        <set>
                <#list template.entity.fieldList as item>
                    <if test="entity.${item.fieldName} != null">
                        ${template.tableInfo.fieldList[item_index].fieldName} = <@poundEl "entity.${item.fieldName},jdbcType=${template.tableInfo.fieldList[item_index].fieldType}"/>,
                    </if>
                </#list>
        </set>
</#assign>
<#assign insertBatchTemplate>
SELECT
            ${baseValueSql}
            FROM DUAL
</#assign>