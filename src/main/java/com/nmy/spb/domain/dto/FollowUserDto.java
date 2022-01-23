package com.nmy.spb.domain.dto;

import lombok.Data;

/**
 * @author nmy
 * @title: FollowUserDto
 * @date 2022-01-22 12:01
 */
@Data
public class FollowUserDto {

    private int id;
    private String user_account;
    private String user_name;
    private String user_badge;
    private String stu_sex;

}
