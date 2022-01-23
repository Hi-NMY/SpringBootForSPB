package com.nmy.spb.domain.pojo;

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
public class Bar{

    private String user_account;
    private String pb_one_id;
    private String pb_date;
    private String pb_article;
    private String pb_image_url;
    private String pb_voice;
    private String pb_video;
    private String pb_topic;
    private String pb_location;
    private int pb_thumb_num;
    private int pb_comment_num;
}
