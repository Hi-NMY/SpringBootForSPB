package com.nmy.spb.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author nmy
 * @title: Topic
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic{

    private int id;

    private String topic_name;
    private int topic_barnum;
    private int topic_attentionnum;
    private String topic_slogan;
    private String topic_image;
}
