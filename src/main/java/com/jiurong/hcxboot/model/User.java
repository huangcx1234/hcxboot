package com.jiurong.hcxboot.model;

import com.jiurong.hcxboot.base.mybatis.annotation.UUID;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiurong.hcxboot.base.mybatis.annotation.CreateTime;
import com.jiurong.hcxboot.base.mybatis.annotation.UpdateTime;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author soyeajr
 * @date 2019-5-23
 * @Description 用户
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @ApiModelProperty(value = "id")
    @UUID
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    @CreateTime
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @UpdateTime
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
}