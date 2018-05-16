<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;

import ${basepackage}.base.BaseServiceImpl;
import ${basepackage}.model.${className};
import ${basepackage}.service.${className}Service;
import org.springframework.stereotype.Service;

/**
 * @author ${author}
 * @date ${cTime}
 * @Description
 */
@Service(value = "${classNameLower}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements ${className}Service {
}