package com.jiurong.hcxboot.controller;

import com.github.pagehelper.PageInfo;
import com.jiurong.hcxboot.model.User;
import com.jiurong.hcxboot.request.user.SaveUser;
import com.jiurong.hcxboot.request.user.PageUser;
import com.jiurong.hcxboot.request.user.UpdateUser;
import com.jiurong.hcxboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author soyeajr
 * @date 2020-6-29
 * @Description 用户
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(description = "用户")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增")
    @PostMapping("/users")
    public User save(@RequestBody @Valid SaveUser saveUser) {
        return userService.save(saveUser);
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }

    @ApiOperation(value = "修改")
    @PutMapping("/users")
    public User update(@RequestBody @Valid UpdateUser updateUser) {
        return userService.update(updateUser);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/users")
    public PageInfo<User> page(PageUser pageUser) {
        return userService.page(pageUser);
    }
}