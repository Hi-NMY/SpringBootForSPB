package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.UserInformationDto;
import com.nmy.spb.service.UserService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: UserController
 * @date 2022-01-29 17:50
 */
@Controller
@ResponseBody
@RequestMapping("/user")
@Api(tags = "用户信息控制")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(path = "/querySchoolTable", method = RequestMethod.GET)
    @ApiOperation(value = "获取课程表", notes = "RequestListJson -> 状态码&SchoolTable")
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String querySchoolTable() {
        return userService.querySchoolTable();
    }

    @RequestMapping(path = "/querySearchUser", method = RequestMethod.POST)
    @ApiOperation(value = "搜索用户", notes = "RequestListJson -> 状态码&SearchUserDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "搜索字符", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String querySearchUser(@RequestParam("search") String search) {
        return userService.querySearchUser(search);
    }

    @RequestMapping(path = "/updateUserPersonalInformation", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户个人信息", notes = "RequestCode -> 状态码\n" +
            "Boot通过UserInformationDto解析\n" +
            "public String updateUserPersonalInformation(UserInformationDto info)")
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已修改", response = RequestCode.class)
    })
    public String updateUserPersonalInformation(UserInformationDto info) {
        return userService.updateUserPersonalInformation(info);
    }

    @RequestMapping(path = "/updateUserIp", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户ip", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "ip", value = "用户ip", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已修改", response = RequestCode.class)
    })
    public String updateUserIp(@RequestParam("user_account") String userAccount, @RequestParam("ip") String ip) {
        return userService.updateUserIp(userAccount, ip);
    }

    @RequestMapping(path = "/updateUserToken", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户token", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已修改", response = RequestCode.class)
    })
    public String updateUserToken(@RequestParam("user_account") String userAccount, @RequestParam("token") String token) {
        return userService.updateUserToken(userAccount, token);
    }

    @RequestMapping(path = "/updateUserHeadImage", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户头像", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "头像", required = true, paramType = "query"),
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateUserHeadImage(@RequestParam("file") MultipartFile file, @RequestParam("user_account") String userAccount) {
        return userService.updateUserHeadImage(file, userAccount);
    }

    @RequestMapping(path = "/updateUserBgImage", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户主页背景", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "背景", required = true, paramType = "query"),
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateUserBgImage(@RequestParam("file") MultipartFile file, @RequestParam("user_account") String userAccount) {
        return userService.updateUserBgImage(file, userAccount);
    }

    @RequestMapping(path = "/updateUserBadgeImage", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户使用徽章", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_badge", value = "徽章", required = true, paramType = "query"),
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String updateUserBadgeImage(@RequestParam("user_badge") String userBadge, @RequestParam("user_account") String userAccount) {
        return userService.updateUserBadgeImage(userBadge, userAccount);
    }

    @RequestMapping(path = "/updateUserPrivacy", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户隐私设置", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_privacy", value = "隐私设置", required = true, paramType = "query"),
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已修改", response = RequestCode.class)
    })
    public String updateUserPrivacy(@RequestParam("user_privacy") String userPrivacy, @RequestParam("user_account") String userAccount) {
        return userService.updateUserPrivacy(userPrivacy, userAccount);
    }

    @RequestMapping(path = "/deleteUserIp", method = RequestMethod.POST)
    @ApiOperation(value = "删除用户ip", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String deleteUserIp(@RequestParam("user_account") String userAccount) {
        return userService.deleteUserIp(userAccount);
    }


}
