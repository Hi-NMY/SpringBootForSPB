package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.service.LikeService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: LikeController
 * @date 2022-01-23 11:42
 */
@Controller
@ResponseBody
@RequestMapping("/like")
@Api(tags = "帖子点赞控制")
public class LikeController {

    @Resource
    LikeService likeService;

    @RequestMapping(path = "/queryLike", method = RequestMethod.POST)
    @ApiOperation(value = "获取点赞列表", notes = "RequestListJson -> 状态码&Like")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryLike(@RequestParam("user_account") String userAccount) {
        return likeService.queryLike(userAccount);
    }

    @RequestMapping(path = "/addLike", method = RequestMethod.POST)
    @ApiOperation(value = "添加点赞", notes = "RequestEntityJson -> 状态码&String(ip)\n" +
            "cache_account --> 客户端判断，若点赞自己，可留空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_one_id", value = "帖子id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "cache_account", value = "被点赞用户账号", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestEntityJson.class),
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String addLike(@RequestParam("pb_one_id") String pbId, @RequestParam("user_account") String userAccount, @RequestParam("cache_account") String cacheAccount) {
        return likeService.addLike(pbId, userAccount, cacheAccount);
    }

    @RequestMapping(path = "/deleteLike", method = RequestMethod.POST)
    @ApiOperation(value = "删除点赞", notes = "RequestEntityJson -> 状态码&String(ip)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_one_id", value = "帖子id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String deleteLike(@RequestParam("pb_one_id") String pbId, @RequestParam("user_account") String userAccount) {
        return likeService.deleteLike(pbId, userAccount);
    }
}
