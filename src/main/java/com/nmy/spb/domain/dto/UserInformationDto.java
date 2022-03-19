package com.nmy.spb.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: UserInformationDto
 * @date 2022-01-29 17:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户个人信息封装")
public class UserInformationDto {

    @ApiModelProperty(value = "账号",required=true)
    private String user_account;

    @ApiModelProperty(value = "描述",required=true)
    private String user_profile;

    @ApiModelProperty(value = "用户名",required=true)
    private String user_name;

    @ApiModelProperty(value = "生日",required=true)
    private String user_birth;

    @ApiModelProperty(value = "住址",required=true)
    private String user_home;

    @ApiModelProperty(value = "喜爱",required=true)
    private String user_favorite;

}
