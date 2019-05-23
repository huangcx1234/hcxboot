<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.request.${classNameLower};

<#assign dateNum=0>
<#list table.columns as column>
<#if column.simpleJavaType =='Date'&& dateNum ==0>
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
<#assign dateNum=1>
</#if>
</#list>
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
@Data
public class Select${className} {
<#list table.columns as column>
    <#if column.columnNameLower !='createTime' && column.columnNameLower !='updateTime'>

    @ApiModelProperty(value = "${column.remarks}")
    <#if column.jdbcSqlTypeName =='TIMESTAMP'>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    </#if>
    <#if column.jdbcSqlTypeName =='DATE'>
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};
    </#if>
</#list>

    @ApiModelProperty(value = "第几页")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;
}