package com.nmy.spb.service;

import com.nmy.spb.domain.dto.CommentDto;
import com.nmy.spb.domain.pojo.Comment;

import java.util.Map;

/**
 * @author nmy
 * @title: CommentService
 * @date 2022-01-22 20:50
 */
public interface CommentService {

    String addComment(CommentDto comment);

    String queryCommentList(String pbId);

    String queryCommentOne(String pbId, String commentId);

    String deleteComment(String pbId, String commentId);

}
