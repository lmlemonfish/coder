<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${template.dao.packageName}.${template.dao.className}">
    <#include "lib.ftl">
    <#--别名-->
    <#assign alias=template.tableInfo.tableName?substring(0,1)/>
    <#--定义变量-->
    <#assign table_id_name = ""/><#assign entity_id_name = ""/><#assign table_id_type = ""/><#assign table_name="${template.tableInfo.tableName}"/>
    <#assign rows = ""/>
    <#assign paramType = "${template.classInfo.packageName}.${template.classInfo.className}" />
    <#include "template.ftl"/>
    <#assign baseColumnList>

    <#list template.tableInfo.fieldList as item>
        <#if item_index == 0>
            <#assign table_id_name="${item.fieldName}"/>
            <#assign table_id_type = "${template.entity.fieldList[item_index].fieldType}">
            <#assign entity_id_name = "${template.entity.fieldList[item_index].fieldName}">
        </#if>
        <#if item_index % 3 == 2>
            ${rows}<#assign rows = ""/>
        </#if>
        <#if item_has_next>
            <#assign rows = rows+alias+".${item.fieldName},"/>
        <#else>
            <#assign rows = rows+alias+".${item.fieldName}"/>${rows}<#assign rows = ""/>
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

    <resultMap id="baseMap" type="${paramType}">
        <#list template.tableInfo.fieldList as item>
            <result column="${item.fieldName}" property="${template.entity.fieldList[item_index].fieldName}" jdbcType="${item.fieldType}" />
        </#list>
    </resultMap>

    <sql id="base_column_list">
        ${baseColumnList}
    </sql>

    <sql id="where_sql">
        ${baseWhereSql}
    </sql>

    <select id="${template.dao.findByIdMethod}" resultMap="baseMap" parameterType="${table_id_type?uncap_first}">
        SELECT <include refid="base_column_list"/> FROM ${table_name} ${alias}
        WHERE ${alias}.${table_id_name} = <@poundEl "${entity_id_name},jdbcType=${table_id_type}"/>
    </select>

    <insert id="${template.dao.addMethod}" parameterType="${paramType}">
        INSERT INTO ${table_name} (${table_insert_value})
        VALUES (${baseValueSql})
    </insert>

    <insert id="${template.dao.addBatchMethod}" parameterType="list">
        INSERT INTO ${table_name} (${table_insert_value})
        <foreach collection="list" item="entity" index="index" separator="union all">
            ${insertBatchTemplate}
        </foreach>
    </insert>

    <update id="${template.dao.updateMethod}" parameterType="${paramType}">
        UPDATE ${table_name}
        <set>
                <#list template.entity.fieldList as item>
                    <if test="${item.fieldName} != null">
                        ${template.tableInfo.fieldList[item_index].fieldName} = <@poundEl "entity.${item.fieldName},jdbcType=${template.tableInfo.fieldList[item_index].fieldType}"/>,
                    </if>
                </#list>
        </set>
        WHERE ${table_id_name} = <@poundEl "entity.${entity_id_name},jdbcType=${table_id_type}"/>
    </update>

    <update id="${template.dao.updateBatchMethod}" parameterType="list">
        <foreach collection="list" item="entity" index="index" separator=";">
            ${updateBatchTemplate}
            WHERE ${table_id_name} = <@poundEl "entity.${entity_id_name},jdbcType=${table_id_type}"/>
        </foreach>
    </update>

    <delete id="${template.dao.delMethod}" parameterType="${table_id_name}">
        <#if entity_sys_flag != ''>
            UPDATE ${table_name} SET ${table_sys_flag} = 0
            WHERE ${table_id_name} = <@poundEl "${entity_id_name}"/>
        <#else>
            DELETE FROM ${table_name} WHERE ${table_id_name} = <@poundEl "${entity_id_name},jdbcType=${table_id_type}"/>
        </#if>
    </delete>

    <delete id="${template.dao.delBatchMethod}" parameterType="list">
        <#if entity_sys_flag != ''>
            UPDATE ${table_name} SET ${table_sys_flag} = 0
            WHERE ${table_id_name} in
            <foreach collection="list" open="(" close=")" separator="," item="id">
                <#if table_id_type=="String"><@poundEl "id" /><#else><@dollarEl "id"/></#if>
            </foreach>
        <#else>
            DELETE FROM ${table_name} WHERE ${table_id_name} in
            <foreach collection="list" open="(" close=")" separator="," item="id">
                <#if table_id_type=="String">
                    <!--id--><@poundEl "id" />
                <#else>
                    <!--id--><@dollarEl "id"/>
                </#if>
            </foreach>
        </#if>
    </delete>

</mapper>

