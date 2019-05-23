package com.jiurong.hcxboot.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author soyeajr
 * @date 2019-5-23
 * @Description 用户
 */
@Data
public class UpdateUser {

    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @Override
    public String toString() {
        return "{"
                + "id:" + id
                + "," + "用户名:" + username
                + "," + "密码:" + password
                + "," + "手机号:" + phone
                + "}";
    }
}