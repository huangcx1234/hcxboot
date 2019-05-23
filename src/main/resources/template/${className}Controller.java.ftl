<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.controller;

import com.github.pagehelper.PageInfo;
import ${basePackage}.config.ClientException;
import ${basePackage}.model.${className};
import ${basePackage}.request.${classNameLower}.Save${className};
import ${basePackage}.request.${classNameLower}.Select${className};
import ${basePackage}.request.${classNameLower}.Update${className};
import ${basePackage}.service.${className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(description = "${tableComment}")
@Slf4j
public class ${className}Controller {

    @Autowired
    private ${className}Service ${classNameLower}Service;

    @ApiOperation(value = "新增")
    @PostMapping("/${classNameLower}s")
    public ${className} save(@RequestBody @Valid Save${className} save${className}) {
        return ${classNameLower}Service.save(save${className});
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping("/${classNameLower}s/{id}")
    public void delete(@PathVariable("id") String id) {
        ${classNameLower}Service.delete(id);
    }

    @ApiOperation(value = "修改")
    @PutMapping("/${classNameLower}s")
    public ${className} update(@RequestBody @Valid Update${className} update${className}) {
        return ${classNameLower}Service.update(update${className});
    }

    @ApiOperation(value = "单个查询")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping("/${classNameLower}s/{id}")
    public ${className} get(@PathVariable("id") String id) {
        ${className} ${classNameLower} = ${classNameLower}Service.get(id);
        if (${classNameLower} == null) {
            throw new ClientException("不存在");
        }
        return ${classNameLower};
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/${classNameLower}s")
    public PageInfo<${className}> page(Select${className} select${className}) {
        return ${classNameLower}Service.page(select${className});
    }
}