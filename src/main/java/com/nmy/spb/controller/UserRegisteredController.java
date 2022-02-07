package com.nmy.spb.controller;

import com.nmy.spb.domain.dto.UserRegisteredDto;
import com.nmy.spb.service.UserRegisteredService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class UserRegisteredController {

    @Resource
    UserRegisteredService userRegisteredService;

    /**
     * @Description: 必要数据：UserRegisteredDto -> user_account,user_password,user_name   file
     * 返回：RequestCode -> 状态码
     * @Param: [userRegisteredDto, file]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 10:00
     */
    @RequestMapping("/userRegistered")
    public String userRegistered(UserRegisteredDto userRegisteredDto, @RequestParam("file") MultipartFile file) {
        return userRegisteredService.userRegistered(userRegisteredDto, file);
    }

}
