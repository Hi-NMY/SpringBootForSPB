package com.nmy.spb.controller;

import com.nmy.spb.service.FollowedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class FollowedController {

    @Resource
    FollowedService followedService;

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&String(follow_account)
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 11:37
     */
    @RequestMapping("/queryFollowedList")
    public String queryFollowedList(@RequestParam("user_account") String userAccount) {
        return followedService.queryFollowedList(userAccount);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&FollowUserDto
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 11:38
     */
    @RequestMapping("/queryFollowedUserList")
    public String queryFollowedUserList(@RequestParam("user_account") String userAccount) {
        return followedService.queryFollowedUserList(userAccount);
    }

}
