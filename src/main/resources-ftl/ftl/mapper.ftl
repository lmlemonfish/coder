<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${template.dao.packageName}.${template.dao.className}">
    <#include "lib.ftl">
    <#assign rows = ""/>
    <#assign baseColumnList>

    <#list template.tableInfo.fieldList as item>
        <#if item_index % 3 == 2>
            ${rows}<#assign rows = ""/>
        </#if>
        <#if item_has_next>
            <#assign rows = rows+"${item.fieldName},"/>
        <#else>
            <#assign rows = rows+"${item.fieldName}"/>${rows}<#assign rows = ""/>
        </#if>
    </#list>
    </#assign>

    <#assign baseWhereSql>

        <#list template.entity.fieldList as item>
        <if test="${item.fieldName} != null and ${item.fieldName} != ''">
            and ${template.tableInfo.fieldList[item_index].fieldName} = <@poundEl "${item.fieldName},jdbcType=${template.tableInfo.fieldList[item_index].fieldType}"/>
        </if>
        </#list>
    </#assign>

    <resultMap id="baseMap" type=${template.classInfo.packageName}.${template.classInfo.className}>
        <#list template.tableInfo.fieldList as item>
            <result column="${item.fieldName}" property="${template.entity.fieldList[item_index].fieldName}" jdbcType="${item.fieldType}"></result>
        </#list>
    </resultMap>

    <sql id="base_column_list">
        ${baseColumnList}
    </sql>

    <sql id="where_sql">
        ${baseWhereSql}
    </sql>
</mapper>