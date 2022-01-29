package com.nmy.spb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: SearchUserDto
 * @date 2022-01-29 17:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchUserDto {

    private String user_account;
    private String user_name;
    private String user_badge;
    private String stu_sex;

}
