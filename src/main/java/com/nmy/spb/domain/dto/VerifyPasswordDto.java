package com.nmy.spb.domain.dto;

import lombok.Data;

/**
 * @author nmy
 * @title: VerifyPasswordDto
 * @date 2022-01-23 17:46
 */
@Data
public class VerifyPasswordDto {

    private String user_account;
    private String user_ip;
    private String user_password;

}
