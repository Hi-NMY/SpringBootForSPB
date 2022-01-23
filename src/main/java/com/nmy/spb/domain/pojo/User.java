package com.nmy.spb.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nmy
 * @title: User
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

    private int id;
    private String user_account;
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
    private String user_ip;
    private String user_token;
}
