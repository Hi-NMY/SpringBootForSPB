package com.nmy.spb.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nmy
 * @title: UserRegisteredDto
 * @date 2022-01-23 19:58
 */
@Data
@ApiModel("注册封装")
public class UserRegisteredDto {

    @ApiModelProperty(value = "账号",required=true)
    private String user_account;

    @ApiModelProperty(value = "密码",required=true)
    private String user_password;

    @ApiModelProperty(value = "用户名",required=true)
    private String user_name;

}
