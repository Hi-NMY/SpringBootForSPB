package com.nmy.spb.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: CollectBarDto
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("收藏帖子封装")
public class CollectBarDto {

    @ApiModelProperty(value = "帖子id",required=true)
    public String pb_one_id;
    @ApiModelProperty(value = "账号",required=true)
    public String user_account;
}