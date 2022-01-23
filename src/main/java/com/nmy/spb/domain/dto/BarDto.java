package com.nmy.spb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author nmy
 * @title: BarDto
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarDto implements Serializable {

    private String user_name;
    private String user_account;
    private String pb_one_id;
    private String pb_date;
    private String pb_article;
    private String pb_image_url;
    private String pb_voice;
    private String pb_video;
    private String pb_topic;
    private String pb_location;
    private String user_badge;
    private int pb_thumb_num;
    private int pb_comment_num;
}
