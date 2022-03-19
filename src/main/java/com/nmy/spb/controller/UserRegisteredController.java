package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.domain.dto.UserRegisteredDto;
import com.nmy.spb.service.UserRegisteredService;
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
 * @title: UserRegisteredController
 * @date 2022-01-23 19:56
 */
@Controller
@ResponseBody
@RequestMapping("/registered")
@Api(tags = "用户注册控制")
public class UserRegisteredController {

    @Resource
    UserRegisteredService userRegisteredService;

    @RequestMapping(path = "/userRegistered", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "RequestCode -> 状态码\n" +
            "Boot根据UserRegisteredDto解析\n" +
            "public String userRegistered(UserRegisteredDto userRegisteredDto, @RequestParam(\"file\") MultipartFile file)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "头像", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "该账号未注册", response = RequestCode.class),
            @ApiResponse(code = 4005, message = "您非本校学生", response = RequestCode.class),
            @ApiResponse(code = 4006, message = "该账号已注册", response = RequestCode.class),
            @ApiResponse(code = 4007, message = "用户名重复", response = RequestCode.class),
            @ApiResponse(code = 4008, message = "头像上传失败", response = RequestCode.class),
            @ApiResponse(code = 200, message = "注册成功", response = RequestCode.class)
    })
    public String userRegistered(UserRegisteredDto userRegisteredDto, @RequestParam("file") MultipartFile file) {
        return userRegisteredService.userRegistered(userRegisteredDto, file);
    }

}
