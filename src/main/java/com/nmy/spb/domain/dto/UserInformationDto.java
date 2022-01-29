package com.nmy.spb.domain.dto;

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
public class UserInformationDto {

    private String user_account;
    private String user_profile;
    private String user_name;
    private String user_birth;
    private String user_home;
    private String user_favorite;

}
