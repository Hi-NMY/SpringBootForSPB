package com.nmy.spb.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author nmy
 * @title: Bar
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("帖子封装")
public class Bar{

    @ApiModelProperty(value = "账号",required=true)
    private String user_account;

    @ApiModelProperty(value = "帖子id")
    private String pb_one_id;

    @ApiModelProperty(value = "日期")
    private String pb_date;

    @ApiModelProperty(value = "文章",required=true)
    private String pb_article;

    @ApiModelProperty(value = "图片(最多4个)")
    private String pb_image_url;

    @ApiModelProperty(value = "音频")
    private String pb_voice;

    @ApiModelProperty(value = "视频")
    private String pb_video;

    @ApiModelProperty(value = "话题",required=true)
    private String pb_topic;

    @ApiModelProperty(value = "定位",required=true)
    private String pb_location;

    @ApiModelProperty(value = "点赞数")
    private int pb_thumb_num;

    @ApiModelProperty(value = "评论数")
    private int pb_comment_num;

}
