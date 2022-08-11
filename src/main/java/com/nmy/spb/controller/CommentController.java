package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.CommentDto;
import com.nmy.spb.service.CommentService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "评论控制")
public class CommentController {

    @Resource
    CommentService commentService;

    @RequestMapping(path = "/addComment", method = RequestMethod.POST)
    @ApiOperation(value = "添加评论", notes = "Boot使用CommentDto解析:\n" +
            "public String addComment(CommentDto comment)\n" +
            "RequestEntityJson -> 状态码&CommentDto(pb_one_id,comment_art,comment_date,comment_user,comment_touser,comment_id,cache_account,user_ip)\n" +
            "客户端独自保存原有用户名\n" +
            "cache_account --> 客户端判断，若评论自己或回复自己，可留空")
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestEntityJson.class),
            @ApiResponse(code = 200, message = "已评论", response = RequestEntityJson.class)
    })
    public String addComment(CommentDto comment) {
        return commentService.addComment(comment);
    }

    @RequestMapping(path = "/queryCommentList", method = RequestMethod.GET)
    @ApiOperation(value = "获取评论列表", notes = "RequestListJson -> 状态码&CommentDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_one_id", value = "帖子id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryCommentList(@RequestParam(value = "pb_one_id") String pbId) {
        return commentService.queryCommentList(pbId);
    }

    @RequestMapping(path = "/queryCommentOne", method = RequestMethod.GET)
    @ApiOperation(value = "获取通知评论", notes = "RequestEntityJson -> 状态码&CommentDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_one_id", value = "帖子id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "comment_id", value = "评论楼层", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "该评论已被删除", response = RequestEntityJson.class),
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryCommentOne(@RequestParam(value = "pb_one_id") String pbId, @RequestParam(value = "comment_id") String commentId) {
        return commentService.queryCommentOne(pbId, commentId);
    }

    @RequestMapping(path = "/deleteComment", method = RequestMethod.POST)
    @ApiOperation(value = "删除评论", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_one_id", value = "帖子id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "comment_id", value = "评论id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "删除评论", response = RequestCode.class)
    })
    public String deleteComment(@RequestParam(value = "pb_one_id") String pbId, @RequestParam(value = "comment_id") String commentId) {
        return commentService.deleteComment(pbId, commentId);
    }
}
