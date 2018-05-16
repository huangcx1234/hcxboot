<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#macro mapperEl value type>${r"#{"}${value},jdbcType=${type}}</#macro>
<#macro mapperElVa value>${r"#{"}${value}}</#macro>
<#macro mapperElPr value>${r"${"}${value}}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basepackage}.dao.${className}Mapper">
    <resultMap id="resultMap" type="${basepackage}.model.${className}">
    <#list table.pkColumns as pk>
        <id property="${pk.columnNameFirstLower}" column="${(pk.sqlName)}"/><!--${(pk.remarks)!''}-->
    </#list>
    <#list table.notPkColumns as column>
        <result property="${column.columnNameFirstLower}" column="${column.sqlName}"/><!--${(column.remarks!'')}-->
    </#list>
    </resultMap>
    <sql id="columnSql">
    <#list table.columns as column>
        ${column.sqlName}<#if column_has_next>,</#if>
    </#list>
    </sql>
    <sql id="propertySql">
    <#list table.columns as column>
        <@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/><#if column_has_next>,</#if>
    </#list>
    </sql>
    <sql id="columnEqProperty">
    <#list table.columns as column>
        ${column.sqlName}=<@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/>,
    </#list>
    </sql>
    <sql id="columnEqPropertyIf">
    <#list table.columns as column>
        <if test="${column.columnNameFirstLower}!=null">${column.sqlName}=<@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/>,</if>
    </#list>
    </sql>
    <sql id="whereIdSql">
        <where>
        <#list table.pkColumns as pk>
            <if test="${pk.columnNameFirstLower}!=null">${pk.columnNameFirstLower} = <@mapperEl pk.columnNameFirstLower pk.jdbcSqlTypeName/></if>
            <if test="${pk.columnNameFirstLower}==null">AND 1=0</if>
        </#list>
        </where>
    </sql>
    <sql id="whereSql">
        <where>
            1=1
            <#list table.columns as column>
            <if test="${column.columnNameFirstLower}!=null">AND ${column.sqlName}=<@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/></if>
            </#list>
        </where>
    </sql>
    <sql id="orderSql">
        order by
        <choose>
            <#list table.columns as column>
            <when test="sort=='${column.columnNameFirstLower}'">${column.sqlName}</when>
            </#list>
            <otherwise>id</otherwise>
        </choose>
        <choose>
            <when test="order=='desc'">desc</when>
            <otherwise>asc</otherwise>
        </choose>
    </sql>

    <!--增删改查基础部分-->
    <insert id="save" parameterType="${basepackage}.model.${className}" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
        INSERT INTO ${classNameLower}
        (<include refid="columnSql"/>)
        VALUES
        (<include refid="propertySql"/>)
    </insert>
    <delete id="deleteById" parameterType="java.lang.Integer">
        DELETE FROM ${classNameLower}
        <include refid="whereIdSql"/>
    </delete>
    <delete id="deleteBySelective" parameterType="${basepackage}.model.${className}">
        DELETE FROM ${classNameLower}
        <include refid="whereSql"/>
    </delete>
    <update id="updateById" parameterType="${basepackage}.model.${className}">
        UPDATE ${classNameLower}
        <set>
            <include refid="columnEqProperty"/>
        </set>
        <include refid="whereIdSql"/>
    </update>
    <update id="updateByIdSelective" parameterType="${basepackage}.model.${className}">
        UPDATE ${classNameLower}
        <set>
            <include refid="columnEqPropertyIf"/>
        </set>
        <include refid="whereIdSql"/>
    </update>
    <select id="selectById" parameterType="java.lang.Integer" resultMap="resultMap">
        SELECT
        <include refid="columnSql"/>
        FROM ${classNameLower}
        <include refid="whereIdSql"/>
    </select>
    <select id="selectBySelective" parameterType="Map" resultMap="resultMap">
        SELECT
        <include refid="columnSql"/>
        FROM ${classNameLower}
        <include refid="whereSql"/>
        <include refid="orderSql"/>
    </select>
    <select id="count" parameterType="Map" resultType="Integer">
        SELECT count(*)
        FROM ${classNameLower}
        <include refid="whereSql"/>
    </select>
</mapper>