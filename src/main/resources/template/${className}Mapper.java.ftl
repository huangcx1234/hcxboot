<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import ${basepackage}.base.BaseMapper;
import ${basepackage}.model.${className};

/**
* @author ${author}
* @date ${cTime}
* @Description
*/
public interface ${className}Mapper extends BaseMapper<${className}>{
}