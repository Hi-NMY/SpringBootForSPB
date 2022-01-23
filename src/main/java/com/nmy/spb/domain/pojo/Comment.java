package com.nmy.spb.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: Comment
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment{

    public String pb_one_id;
    public String comment_art;
    public String comment_date;
    public String comment_user;
    public String comment_touser;
    public int comment_id;
}
