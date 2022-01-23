package com.nmy.spb.controller;

import com.nmy.spb.service.FollowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: FollowController
 * @date 2022-01-23 9:52
 */
@Controller
@ResponseBody
@RequestMapping("/follow")
public class FollowController {

    @Resource
    FollowService followService;

    @PostMapping("/queryFollowList")
    public String queryFollowList(@RequestParam("user_account")String userAccount){
        return followService.queryFollowList(userAccount);
    }

    @PostMapping("/queryFollowUserList")
    public String queryFollowUserList(@RequestParam("user_account")String userAccount) {
        return followService.queryFollowUserList(userAccount);
    }

    @PostMapping("/addFollow")
    public String addFollow(@RequestParam("cache_account")String cacheAccount,@RequestParam("user_account")String userAccount){
        return followService.addFollow(cacheAccount,userAccount);
    }

    @PostMapping("/deleteFollow")
    public String deleteFollow(@RequestParam("cache_account")String cacheAccount,@RequestParam("user_account")String userAccount){
        return followService.deleteFollow(cacheAccount, userAccount);
    }
}
