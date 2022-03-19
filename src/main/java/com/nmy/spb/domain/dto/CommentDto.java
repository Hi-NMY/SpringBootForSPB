package com.nmy.spb.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("评论信息封装")
public class CommentDto implements Serializable {

    @ApiModelProperty(value = "帖子id", required = true)
    public String pb_one_id;

    @ApiModelProperty(value = "评论内容", required = true)
    public String comment_art;

    @ApiModelProperty(value = "评论时间")
    public String comment_date;

    @ApiModelProperty(value = "评论用户账号", required = true)
    public String comment_user;

    @ApiModelProperty(value = "评论用户用户名")
    public String user_name;

    @ApiModelProperty(value = "回复用户账号", required = true)
    public String comment_touser;

    @ApiModelProperty(value = "回复用户用户名")
    public String user_toname;

    @ApiModelProperty(value = "评论楼层",notes = "传入String类型",example = "1")
    public int comment_id;

    @ApiModelProperty(value = "被评论帖子账号")
    public String cache_account;

    @ApiModelProperty(value = "被评论帖子用户ip")
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
