package com.nmy.spb.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author nmy
 * @title: VerifyPasswordDto
 * @date 2022-01-23 17:46
 */
@Data
@AllArgsConstructor
@ApiModel("验证密码封装")
public class VerifyPasswordDto{

    @ApiModelProperty(value = "账号",required=true)
    private String user_account;
    @ApiModelProperty(value = "ip",required=true)
    private String user_ip;
    @ApiModelProperty(value = "密码",required=true)
    private String user_password;

}
