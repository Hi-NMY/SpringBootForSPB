package com.nmy.spb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author nmy
 * @title: UpdatePasswordDto
 * @date 2022-01-23 17:40
 */
@Data
public class UpdatePasswordDto {

    private String user_account;
    private String user_password_old;
    private String user_password;

}
