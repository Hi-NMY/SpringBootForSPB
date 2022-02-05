package com.nmy.spb.controller;

import com.nmy.spb.domain.dto.UpdatePasswordDto;
import com.nmy.spb.domain.dto.VerifyPasswordDto;
import com.nmy.spb.service.AccountSecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: AccountSecurityController
 * @date 2022-01-23 17:07
 */
@Controller
@ResponseBody
@RequestMapping("/accountSecurity")
public class AccountSecurityController {

    @Resource
    AccountSecurityService accountSecurityService;

    /**
     * @Description: 必要数据：UpdatePasswordDto -> user_account,user_password_old,user_password
     * 返回：RequestCode -> 状态码
     * @Param: [updatePasswordDto]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 11:13
     */
    @RequestMapping("/updateUserPassword")
    public String updateUserPassword(UpdatePasswordDto updatePasswordDto) {
        return accountSecurityService.updateUserPassword(updatePasswordDto);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestEntityJson -> 状态码&UserDto
     * 特殊：传入账号，若账号存在查询用户信息并返回
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 11:14
     */
    @RequestMapping("/queryVerifyAndUserFull")
    public String queryVerifyAndUserFull(@RequestParam("user_account") String userAccount) {
        return accountSecurityService.queryVerifyAndUserFull(userAccount);
    }

    /**
     * @Description: 必要数据：VerifyPasswordDto -> user_account,user_ip,user_password
     * 返回：RequestCode -> 状态码
     * @Param: [verifyPasswordDto]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 11:16
     */
    @RequestMapping("/queryVerifyUserPassword")
    public String queryVerifyUserPassword(VerifyPasswordDto verifyPasswordDto) {
        return accountSecurityService.queryVerifyUserPassword(verifyPasswordDto);
    }

}
