<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.controller;

import ${basepackage}.model.${className};
import ${basepackage}.service.${className}Service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ${author}
 * @date ${cTime}
 * @Description
 */
@Controller
@RequestMapping(value = "/${classNameLower}")
public class ${className}Controller {
    Logger logger = Logger.getLogger(${className}Controller.class);

    @Autowired
    private ${className}Service ${classNameLower}Service;

    @ResponseBody
    @PostMapping("/save")
    public Object save(${className} ${classNameLower}) {
        int result = ${classNameLower}Service.save(${classNameLower});
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/deleteById")
    public Object deleteById(${className} ${classNameLower}) {
        int result = ${classNameLower}Service.deleteById(${classNameLower}.getId());
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/deleteBySelective")
    public Object deleteBySelective(${className} ${classNameLower}) {
        int result = ${classNameLower}Service.deleteBySelective(${classNameLower});
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/updateById")
    public Object updateById(${className} ${classNameLower}) {
        int result = ${classNameLower}Service.updateById(${classNameLower});
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/updateByIdSelective")
    public Object updateByIdSelective(${className} ${classNameLower}) {
        int result = ${classNameLower}Service.updateByIdSelective(${classNameLower});
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/selectById")
    public Object selectById(${className} ${classNameLower}) {
        return ${classNameLower}Service.selectById(${classNameLower}.getId());
    }

    @ResponseBody
    @PostMapping("/selectBySelective")
    public Object selectBySelective(${className} ${classNameLower}) {
        Map<String, Object> params = new HashMap<>();
        <#list table.columns as column>
        params.put("${column.columnNameLower}", ${classNameLower}.get${column.columnName}());
        </#list>
        return ${classNameLower}Service.selectBySelective(params);
    }

    @ResponseBody
    @PostMapping("/count")
    public int count(${className} ${classNameLower}) {
        Map<String, Object> params = new HashMap<>();
        <#list table.columns as column>
        params.put("${column.columnNameLower}", ${classNameLower}.get${column.columnName}());
        </#list>
        return ${classNameLower}Service.count(params);
    }

    @ResponseBody
    @PostMapping("/pageInfo")
    public Object pageInfo(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            ${className} ${classNameLower}) {
        Map<String, Object> params = new HashMap<>();
        <#list table.columns as column>
        params.put("${column.columnNameLower}", ${classNameLower}.get${column.columnName}());
        </#list>
        return ${classNameLower}Service.pageInfo(pageNum, pageSize, params);
    }
}