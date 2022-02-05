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

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&String(followed_account)
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 11:31
     */
    @PostMapping("/queryFollowList")
    public String queryFollowList(@RequestParam("user_account") String userAccount) {
        return followService.queryFollowList(userAccount);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&FollowUserDto
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 11:32
     */
    @PostMapping("/queryFollowUserList")
    public String queryFollowUserList(@RequestParam("user_account") String userAccount) {
        return followService.queryFollowUserList(userAccount);
    }

    /**
     * @Description: 必要数据：follow_account,followed_account
     * 返回：RequestEntityJson -> 状态码&String(ip)  |  RequestJson -> 状态码
     * 特殊：客户端使用RequestEntityJson接收
     * @Param: [followAccount, followedAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 11:33
     */
    @PostMapping("/addFollow")
    public String addFollow(@RequestParam("follow_account") String followAccount, @RequestParam("followed_account") String followedAccount) {
        return followService.addFollow(followAccount, followedAccount);
    }

    /**
     * @Description: 必要数据：follow_account,followed_account
     * 返回：RequestCode -> 状态码
     * @Param: [followAccount, followedAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 11:34
     */
    @PostMapping("/deleteFollow")
    public String deleteFollow(@RequestParam("follow_account") String followAccount, @RequestParam("followed_account") String followedAccount) {
        return followService.deleteFollow(followAccount, followedAccount);
    }
}
