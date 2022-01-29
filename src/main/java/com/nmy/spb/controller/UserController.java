package com.nmy.spb.controller;

import com.nmy.spb.domain.dto.UserInformationDto;
import com.nmy.spb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class UserController {

    @Resource
    UserService userService;

    /**
     * @Description: 必要数据：无
     * 返回：RequestListJson -> 状态码&SchoolTable
     * @Param: []
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 18:15
     */
    @RequestMapping("/querySchoolTable")
    public String querySchoolTable() {
        return userService.querySchoolTable();
    }

    /**
     * @Description: 必要数据：search
     * 返回：RequestListJson -> 状态码&SearchUserDto
     * @Param: [search]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 18:15
     */
    @RequestMapping("/querySearchUser")
    public String querySearchUser(@RequestParam("search") String search) {
        return userService.querySearchUser(search);
    }

    /**
     * @Description: 必要数据：UserInformationDto -> 全部
     * 返回：RequestJson -> 状态码
     * @Param: [info]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 18:16
     */
    @RequestMapping("/updateUserPersonalInformation")
    public String updateUserPersonalInformation(UserInformationDto info) {
        return userService.updateUserPersonalInformation(info);
    }

    /**
     * @Description: 必要数据：user_account   file
     * 返回：RequestJson -> 状态码
     * @Param: [file, userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 18:16
     */
    @RequestMapping("/updateUserHeadImage")
    public String updateUserHeadImage(@RequestParam("file") MultipartFile file, @RequestParam("user_account") String userAccount) {
        return userService.updateUserHeadImage(file, userAccount);
    }

    /**
     * @Description: 必要数据：user_account   file
     * 返回：RequestJson -> 状态码
     * @Param: [file, userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 18:16
     */
    @RequestMapping("/updateUserBgImage")
    public String updateUserBgImage(@RequestParam("file") MultipartFile file, @RequestParam("user_account") String userAccount) {
        return userService.updateUserBgImage(file, userAccount);
    }

    /**
     * @Description: 必要数据：user_account,user_badge
     * 返回：RequestJson -> 状态码
     * @Param: [userBadge, userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 18:16
     */
    @RequestMapping("/updateUserBadgeImage")
    public String updateUserBadgeImage(@RequestParam("user_badge") String userBadge, @RequestParam("user_account") String userAccount) {
        return userService.updateUserBadgeImage(userBadge, userAccount);
    }

    /**
     * @Description: 必要数据：user_account,user_privacy
     * 返回：RequestJson -> 状态码
     * @Param: [userPrivacy, userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 18:16
     */
    @RequestMapping("/updateUserPrivacy")
    public String updateUserPrivacy(@RequestParam("user_privacy") String userPrivacy, @RequestParam("user_account") String userAccount) {
        return userService.updateUserPrivacy(userPrivacy, userAccount);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 18:16
     */
    @RequestMapping("/deleteUserIp")
    public String deleteUserIp(@RequestParam("user_account") String userAccount) {
        return userService.deleteUserIp(userAccount);
    }


}
