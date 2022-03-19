package com.nmy.spb.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nmy
 * @title: UpdatePasswordDto
 * @date 2022-01-23 17:40
 */
@Data
@ApiModel("修改密码封装")
public class UpdatePasswordDto {

    @ApiModelProperty(value = "账号",required=true)
    private String user_account;
    @ApiModelProperty(value = "旧密码",required=true)
    private String user_password_old;
    @ApiModelProperty(value = "新密码",required=true)
    private String user_password;

}
