package com.nmy.spb.controller;

import com.nmy.spb.domain.dto.CommentDto;
import com.nmy.spb.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    /**
     * @Description: 必要数据：CommentDto --> pb_one_id,comment_art,comment_user,comment_touser
     * 非必要数据：cache_account --> 客户端判断，若评论自己或回复自己，可留空
     * 处理：comment_date,comment_id,user_ip
     * 返回：RequestEntityJson ->
     * 状态码&CommentDto --> pb_one_id,comment_art,comment_date,comment_user,comment_touser,comment_id,cache_account,user_ip
     * 特殊：客户端独自保存原有用户名
     * @Param: [comment]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 0:16
     */
    @PostMapping("/addComment")
    public String addComment(CommentDto comment) {
        return commentService.addComment(comment);
    }

    /**
     * @Description: 必要数据：pb_one_id
     * 返回：RequestListJson -> 状态码&CommentDto
     * @Param: [pbId]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 0:27
     */
    @PostMapping("/queryCommentList")
    public String queryCommentList(@RequestParam(value = "pb_one_id") String pbId) {
        return commentService.queryCommentList(pbId);
    }

    /**
     * @Description: 必要数据：pb_one_id,comment_id
     * 返回：RequestEntityJson -> 状态码&CommentDto
     * @Param: [pbId, commentId]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 0:29
     */
    @PostMapping("/queryCommentOne")
    public String queryCommentOne(@RequestParam(value = "pb_one_id") String pbId, @RequestParam(value = "comment_id") String commentId) {
        return commentService.queryCommentOne(pbId, commentId);
    }

    /**
     * @Description: 必要数据：pb_one_id,comment_id
     * 返回：RequestCode -> 状态码
     * @Param: [pbId, commentId]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 0:31
     */
    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam(value = "pb_one_id") String pbId, @RequestParam(value = "comment_id") String commentId) {
        return commentService.deleteComment(pbId, commentId);
    }
}
