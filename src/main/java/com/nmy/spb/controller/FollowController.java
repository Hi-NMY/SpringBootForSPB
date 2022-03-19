package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.service.FollowService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: FollowController
 * @date 2022-01-23 9:52
 */
@Controller
@ResponseBody
@RequestMapping("/follow")
@Api(tags = "好友关注控制")
public class FollowController {

    @Resource
    FollowService followService;

    @RequestMapping(path = "/queryFollowList", method = RequestMethod.POST)
    @ApiOperation(value = "获取关注用户列表", notes = "RequestListJson -> 状态码&String(followed_account)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryFollowList(@RequestParam("user_account") String userAccount) {
        return followService.queryFollowList(userAccount);
    }

    @RequestMapping(path = "/queryFollowCount", method = RequestMethod.POST)
    @ApiOperation(value = "获取关注数", notes = "RequestEntityJson -> 状态码&String(count)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryFollowCount(@RequestParam("user_account") String userAccount) {
        return followService.queryFollowCount(userAccount);
    }

    @RequestMapping(path = "/queryFollowedCount", method = RequestMethod.POST)
    @ApiOperation(value = "获取被关注数", notes = "RequestEntityJson -> 状态码&String(count)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryFollowedCount(@RequestParam("user_account") String userAccount) {
        return followService.queryFollowedCount(userAccount);
    }

    @RequestMapping(path = "/queryFollowUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取关注用户信息列表", notes = "RequestListJson -> 状态码&FollowUserDtov")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryFollowUserList(@RequestParam("user_account") String userAccount) {
        return followService.queryFollowUserList(userAccount);
    }

    @RequestMapping(path = "/addFollow", method = RequestMethod.POST)
    @ApiOperation(value = "添加关注", notes = "RequestEntityJson -> 状态码&String(ip)  |  RequestJson -> 状态码\n" +
            "客户端使用RequestEntityJson接收")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "follow_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "followed_account", value = "被关注用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已关注", response = RequestEntityJson.class),
            @ApiResponse(code = 2000, message = "已关注", response = RequestCode.class),
    })
    public String addFollow(@RequestParam("follow_account") String followAccount, @RequestParam("followed_account") String followedAccount) {
        return followService.addFollow(followAccount, followedAccount);
    }

    @RequestMapping(path = "/deleteFollow", method = RequestMethod.POST)
    @ApiOperation(value = "删除关注", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "follow_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "followed_account", value = "被关注用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "取消关注", response = RequestCode.class)
    })
    public String deleteFollow(@RequestParam("follow_account") String followAccount, @RequestParam("followed_account") String followedAccount) {
        return followService.deleteFollow(followAccount, followedAccount);
    }
}
