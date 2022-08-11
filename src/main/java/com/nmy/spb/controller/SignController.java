package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.service.SignService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: SignController
 * @date 2022-01-23 12:18
 */
@Controller
@ResponseBody
@RequestMapping("/sign")
@Api(tags = "签到控制")
public class SignController {

    @Resource
    SignService signService;

    @RequestMapping(path = "/queryUserSign", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户签到信息", notes = "RequestEntityJson -> 状态码&Sign")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryUserSign(@RequestParam("user_account") String userAccount) {
        return signService.queryUserSign(userAccount);
    }

    @RequestMapping(path = "/queryUserBadge", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户徽章", notes = "RequestEntityJson -> 状态码&UserBadgeDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryUserBadge(@RequestParam("user_account") String userAccount) {
        return signService.queryUserBadge(userAccount);
    }

    @RequestMapping(path = "/updateSignDay", method = RequestMethod.POST)
    @ApiOperation(value = "更新签到天数", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateSignDay(@RequestParam("user_account") String userAccount) {
        return signService.updateSignDay(userAccount);
    }

    @RequestMapping(path = "/updateSignRight", method = RequestMethod.POST)
    @ApiOperation(value = "更新未签到", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateSignRight(@RequestParam("user_account") String userAccount) {
        return signService.updateSignRight(userAccount);
    }

    @RequestMapping(path = "/updateSignDayAndRight", method = RequestMethod.POST)
    @ApiOperation(value = "更新断签到", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateSignDayAndRight(@RequestParam("user_account") String userAccount) {
        return signService.updateSignDayAndRight(userAccount);
    }


    @RequestMapping(path = "/updateSignDayAndRightAndCoin", method = RequestMethod.POST)
    @ApiOperation(value = "更新已签到", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateSignDayAndRightAndCoin(@RequestParam("user_account") String userAccount, @RequestParam("sign_day") String signDay, @RequestParam("coin") String coin) {
        return signService.updateSignDayAndRightAndCoin(userAccount, signDay, coin);
    }

    @RequestMapping(path = "/updateSignCoin", method = RequestMethod.POST)
    @ApiOperation(value = "更新金币", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "coin", value = "金币数", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateSignCoin(@RequestParam("user_account") String userAccount, @RequestParam("coin") String coin) {
        return signService.updateSignCoin(userAccount, coin);
    }

    @RequestMapping(path = "/updateSignStarBadge", method = RequestMethod.POST)
    @ApiOperation(value = "更新星座徽章", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sign_star_badge", value = "图片地址", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateSignStarBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_star_badge") String starBadge) {
        return signService.updateSignStarBadge(userAccount, starBadge);
    }

    @RequestMapping(path = "/updateSignLikeBadge", method = RequestMethod.POST)
    @ApiOperation(value = "更新点赞徽章", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sign_like_badge", value = "图片地址", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateSignLikeBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_like_badge") String likeBadge) {
        return signService.updateSignLikeBadge(userAccount, likeBadge);
    }

    @RequestMapping(path = "/updateSignTaskBadge", method = RequestMethod.POST)
    @ApiOperation(value = "更新任务徽章", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sign_tesk_badge", value = "图片地址", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateSignTaskBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_tesk_badge") String taskBadge) {
        return signService.updateSignTaskBadge(userAccount, taskBadge);
    }


}
