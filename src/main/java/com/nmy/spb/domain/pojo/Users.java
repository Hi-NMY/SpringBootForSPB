package com.nmy.spb.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: Users
 * @date 2022-01-22 9:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private int id;
    private String user_account;
    private String user_password;
    private String user_secret_protection;

}
