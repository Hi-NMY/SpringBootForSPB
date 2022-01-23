package com.nmy.spb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nmy
 * @title: RongUserDto
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RongUserDto {

    private int code;
    private String userId;
    private String token;

}
