<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.exception.ClientException;
import ${basePackage}.mapper.${className}Mapper;
import ${basePackage}.model.${className};
import ${basePackage}.request.${classNameLower}.Save${className};
import ${basePackage}.request.${classNameLower}.Page${className};
import ${basePackage}.request.${classNameLower}.Update${className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
@Service(value = "${classNameLower}Service")
public class ${className}Service {

    @Value("${r"${page.size}"}")
    private Integer pageSize;

    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;

    public ${className} save(Save${className} save${className}) {
        ${className} ${classNameLower} = new ${className}();
        <#list table.columns as column>
        <#if column.columnNameLower !='id' && column.columnNameLower !='createTime' && column.columnNameLower !='updateTime'>
        ${classNameLower}.set${column.columnName}(save${className}.get${column.columnName}());
        </#if>
        </#list>
        ${classNameLower}Mapper.save(${classNameLower});
        return ${classNameLower};
    }

    public void delete(String id) {
        if (${classNameLower}Mapper.deleteById(id) == 0) {
            throw new ClientException("不存在");
        }
    }

    public ${className} update(Update${className} update${className}) {
        ${className} ${classNameLower} = ${classNameLower}Mapper.selectById(update${className}.getId());
        if (${classNameLower} == null) {
            throw new ClientException("不存在");
        }
        <#list table.columns as column>
        <#if column.columnNameLower !='id' && column.columnNameLower !='createTime' && column.columnNameLower !='updateTime'>
        ${classNameLower}.set${column.columnName}(update${className}.get${column.columnName}());
        </#if>
        </#list>
        ${classNameLower}Mapper.updateById(${classNameLower});
        return ${classNameLower};
    }

    public PageInfo<${className}> page(Page${className} page${className}) {
        PageHelper.startPage(page${className}.getPageNum() == null ? 1 : page${className}.getPageNum(), page${className}.getPageSize() == null ? pageSize : page${className}.getPageSize());
        List<${className}> list = ${classNameLower}Mapper.selectBySelective(page${className}.toMap());
        return new PageInfo<>(list);
    }
}