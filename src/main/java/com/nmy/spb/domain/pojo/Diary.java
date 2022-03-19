package com.nmy.spb.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nmy
 * @title: Diary
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("日记封装")
public class Diary {

    @ApiModelProperty(value = "id")
    public int id;

    @ApiModelProperty(value = "账号",required=true)
    public String user_account;

    @ApiModelProperty(value = "时间")
    public String dia_date;

    @ApiModelProperty(value = "天气Code",required=true)
    public int dia_weather;

    @ApiModelProperty(value = "日记内容",required=true)
    public String dia_message;

    @ApiModelProperty(value = "账号")
    public String dia_image;
}
