package com.nmy.spb.domain.dto;

import lombok.Data;

/**
 * @author nmy
 * @title: AppVerisonDto
 * @date 2022-02-07 17:16
 */
@Data
public class AppVersionDto {

    private int versionCode;
    private String versionName;
    private String detailed;

}
