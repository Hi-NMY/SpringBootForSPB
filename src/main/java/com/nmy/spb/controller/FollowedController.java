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

    @RequestMapping("/queryFollowedList")
    public String queryFollowedList(@RequestParam("user_account") String userAccount){
        return followedService.queryFollowedList(userAccount);
    }

    @RequestMapping("/queryFollowedUserList")
    public String queryFollowedUserList(@RequestParam("user_account") String userAccount){
        return followedService.queryFollowedUserList(userAccount);
    }

}
