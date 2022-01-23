package com.nmy.spb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: AttentionTopicDto
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttentionTopicDto {

    private int id;
    private String topic_name;
    private int topic_barnum;
    private int topic_attentionnum;
    private String topic_slogan;
    private String topic_image;
    public String topic_date;
}
