<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model;

public class ${className} {
<#list table.columns as column>
    /**
    * ${column.remarks}
    */
    private ${column.simpleJavaType} ${column.columnNameLower};
</#list>
<@generateJavaColumns/>
}

<#macro generateJavaColumns>
    <#list table.columns as column>

    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
    </#list>
</#macro>