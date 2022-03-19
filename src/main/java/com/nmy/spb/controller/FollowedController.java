package com.nmy.spb.controller;

import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.service.FollowedService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: FollowedController
 * @date 2022-01-23 9:54
 */
@Controller
@ResponseBody
@RequestMapping("/followed")
@Api(tags = "好友被关注控制")
public class FollowedController {

    @Resource
    FollowedService followedService;

    @RequestMapping(path = "/queryFollowedList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户被关注列表", notes = "RequestListJson -> 状态码&String(follow_account)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryFollowedList(@RequestParam("user_account") String userAccount) {
        return followedService.queryFollowedList(userAccount);
    }

    @RequestMapping(path = "/queryFollowedUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户被关注信息列表", notes = "RequestListJson -> 状态码&FollowUserDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryFollowedUserList(@RequestParam("user_account") String userAccount) {
        return followedService.queryFollowedUserList(userAccount);
    }

}
