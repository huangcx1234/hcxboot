<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.mapper;

import ${basePackage}.model.${className};
import ${basePackage}.base.BaseMapper;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
public interface ${className}Mapper extends BaseMapper<${className}> {
}