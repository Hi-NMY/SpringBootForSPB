package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.CommentDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author nmy
 * @title: CommentMapper
 * @date 2022-01-22 10:49
 */
public interface CommentMapper {

    @Select("SELECT IFNULL(MAX(comment_id),0) " +
            "from postbar_comment " +
            "WHERE postbar_comment.pb_one_id = #{pb_one_id}" )
    int queryMaxCommentId(@Param("pb_one_id") String pbId);

    @Insert("INSERT into postbar_comment(pb_one_id,comment_art,comment_date,comment_user,comment_touser,comment_id) " +
            "values(#{pb_one_id},#{comment_art},#{comment_date},#{comment_user},#{comment_touser},#{comment_id})")
    int addComment(CommentDto comment);

    @Select("SELECT t.pb_one_id,t.comment_art,t.comment_date,t.comment_user,t1.user_name,t.comment_touser,t2.user_name as user_toname,t.comment_id " +
            "FROM postbar_comment t left join user t1 " +
            "on t.comment_user = t1.user_account " +
            "left join user t2 on t.comment_touser = t2.user_account " +
            "WHERE t.pb_one_id = #{pb_one_id} " +
            "ORDER BY t.comment_date ASC")
    List<CommentDto> queryCommentList(@Param("pb_one_id") String pbId);

    @Select("SELECT t.pb_one_id,t.comment_art,t.comment_date,t.comment_user,t1.user_name,t.comment_touser,t2.user_name as user_toname,t.comment_id " +
            "FROM postbar_comment t left join user t1 " +
            "on t.comment_user = t1.user_account " +
            "left join user t2 on t.comment_touser = t2.user_account " +
            "WHERE t.pb_one_id = #{pb_one_id} AND t.comment_id = #{comment_id}")
    CommentDto queryCommentOne(@Param("pb_one_id") String pbId,@Param("comment_id") String commentId);

    @Delete("DELETE FROM postbar_comment " +
            "where pb_one_id = #{pb_one_id} AND comment_id = #{comment_id}")
    int deleteComment(@Param("pb_one_id") String pbId,@Param("comment_id") String commentId);

}
