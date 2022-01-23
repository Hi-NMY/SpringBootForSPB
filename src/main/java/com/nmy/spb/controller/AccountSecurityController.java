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
public class AccountSecurityController{

    @Resource
    AccountSecurityService accountSecurityService;

    @RequestMapping("/updateUserPassword")
    public String updateUserPassword(UpdatePasswordDto updatePasswordDto){
        return accountSecurityService.updateUserPassword(updatePasswordDto);
    }

    @RequestMapping("/queryVerifyAndUserFull")
    public String queryVerifyAndUserFull(@RequestParam("user_account")String userAccount){
        return accountSecurityService.queryVerifyAndUserFull(userAccount);
    }

    @RequestMapping("/queryVerifyUserPassword")
    public String queryVerifyUserPassword(VerifyPasswordDto verifyPasswordDto){
        return accountSecurityService.queryVerifyUserPassword(verifyPasswordDto);
    }

}
