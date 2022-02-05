package com.nmy.spb.controller;

import com.nmy.spb.service.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: LikeController
 * @date 2022-01-23 11:42
 */
@Controller
@ResponseBody
@RequestMapping("/like")
public class LikeController {

    @Resource
    LikeService likeService;

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&Like
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 19:57
     */
    @RequestMapping("/queryLike")
    public String queryLike(@RequestParam("user_account") String userAccount) {
        return likeService.queryLike(userAccount);
    }

    /**
     * @Description: 必要数据：pb_one_id,user_account
     * 非必要数据：cache_account
     * 返回：RequestEntityJson -> 状态码&String(ip)  |  RequestJson -> 状态码
     * @Param: [pbId, userAccount, cacheAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 21:59
     */
    @RequestMapping("/addLike")
    public String addLike(@RequestParam("pb_one_id") String pbId, @RequestParam("user_account") String userAccount, @RequestParam("cache_account") String cacheAccount) {
        return likeService.addLike(pbId, userAccount, cacheAccount);
    }

    /**
     * @Description: 必要数据：pb_one_id,user_account
     * 返回：RequestCode -> 状态码
     * @Param: [pbId, userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 19:59
     */
    @RequestMapping("/deleteLike")
    public String deleteLike(@RequestParam("pb_one_id") String pbId, @RequestParam("user_account") String userAccount) {
        return likeService.deleteLike(pbId, userAccount);
    }
}
