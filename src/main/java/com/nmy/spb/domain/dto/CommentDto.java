package com.nmy.spb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author nmy
 * @title: CommentDto
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable {

    public String pb_one_id;
    public String comment_art;
    public String comment_date;
    public String comment_user;
    public String user_name;
    public String comment_touser;
    public String user_toname;
    public int comment_id;
    public String cache_account;
    public String user_ip;

    public String getPb_one_id() {
        return pb_one_id == null ? "" : pb_one_id;
    }

    public String getComment_art() {
        return comment_art == null ? "" : comment_art;
    }

    public String getComment_date() {
        return comment_date == null ? "" : comment_date;
    }

    public String getComment_user() {
        return comment_user == null ? "" : comment_user;
    }

    public String getUser_name() {
        return user_name == null ? "" : user_name;
    }

    public String getComment_touser() {
        return comment_touser == null ? "" : comment_touser;
    }

    public String getUser_toname() {
        return user_toname == null ? "" : user_toname;
    }

    public String getCache_account() {
        return cache_account == null ? "" : cache_account;
    }

    public String getUser_ip() {
        return user_ip == null ? "" : user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }
}
