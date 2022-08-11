package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.domain.dto.UpdatePasswordDto;
import com.nmy.spb.domain.dto.VerifyPasswordDto;
import com.nmy.spb.service.AccountSecurityService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: AccountSecurityController
 * @date 2022-01-23 17:07
 */
@Controller
@ResponseBody
@RequestMapping("/accountSecurity")
@Api(tags = "账户验证控制")
public class AccountSecurityController {

    @Resource
    AccountSecurityService accountSecurityService;

    @RequestMapping(path = "/updateUserPassword", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码", notes = "Boot使用UpdatePasswordDto解析:\n" +
            "public String updateUserPassword(UpdatePasswordDto updatePasswordDto)\n" +
            "RequestCode -> 状态码")
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "密码修改成功", response = RequestCode.class)
    })
    public String updateUserPassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        return accountSecurityService.updateUserPassword(updatePasswordDto);
    }

    @RequestMapping(path = "/queryVerifyAndUserFull", method = RequestMethod.POST)
    @ApiOperation(value = "验证账号", notes = "RequestEntityJson -> 状态码&UserDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "该账号未注册", response = RequestEntityJson.class),
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryVerifyAndUserFull(@RequestParam("user_account") String userAccount) {
        return accountSecurityService.queryVerifyAndUserFull(userAccount);
    }

    @RequestMapping(path = "/queryVerifyUserPassword", method = RequestMethod.POST)
    @ApiOperation(value = "验证密码", notes = "Boot使用VerifyPasswordDto解析:\n" +
            "public String queryVerifyUserPassword(VerifyPasswordDto verifyPasswordDto)\n" +
            "RequestCode -> 状态码")
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String queryVerifyUserPassword(VerifyPasswordDto verifyPasswordDto) {
        return accountSecurityService.queryVerifyUserPassword(verifyPasswordDto);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(@RequestBody VerifyPasswordDto verifyPasswordDto) {
        return accountSecurityService.logout(verifyPasswordDto);
    }

}
