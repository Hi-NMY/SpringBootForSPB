package com.nmy.spb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author nmy
 * @title: UserDto
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private int id;
    private String user_account;
    private String user_password;
    private String user_name;
    private String user_birth;
    private String user_home;
    private String user_favorite;
    private String user_profile;
    private String user_head_image;
    private String user_bg_image;
    private String user_badge;
    private int user_longday;
    private String user_privacy;
    private String stu_sex;
    private String stu_name;
    private String user_ip;
    private String user_token;
}
