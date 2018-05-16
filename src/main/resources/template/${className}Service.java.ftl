<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import ${basepackage}.base.BaseService;
import ${basepackage}.model.${className};

/**
 * @author ${author}
 * @date ${cTime}
 * @Description
 */
public interface ${className}Service extends BaseService<${className}>{
}