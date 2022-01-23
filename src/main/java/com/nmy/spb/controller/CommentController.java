package com.nmy.spb.controller;

import com.nmy.spb.domain.dto.CommentDto;
import com.nmy.spb.domain.pojo.Comment;
import com.nmy.spb.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author nmy
 * @title: CommentController
 * @date 2022-01-22 20:30
 */
@Controller
@ResponseBody
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentService commentService;

    @PostMapping("/addComment")
    public String addComment(CommentDto comment){
        return commentService.addComment(comment);
    }

    @PostMapping("/queryCommentList")
    public String queryCommentList(@RequestParam(value = "pb_one_id") String pbId){
        return commentService.queryCommentList(pbId);
    }

    @PostMapping("/queryCommentOne")
    public String queryCommentOne(@RequestParam(value = "pb_one_id") String pbId,@RequestParam(value = "comment_id") String commentId){
        return commentService.queryCommentOne(pbId,commentId);
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam(value = "pb_one_id") String pbId,@RequestParam(value = "comment_id") String commentId){
        return commentService.deleteComment(pbId, commentId);
    }
}
