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

    @RequestMapping("/queryLike")
    public String queryLike(@RequestParam("user_account") String userAccount) {
        return likeService.queryLike(userAccount);
    }

    @RequestMapping("/addLike")
    public String addLike(@RequestParam("pb_one_id") String pbId, @RequestParam("user_account") String userAccount) {
        return likeService.addLike(pbId, userAccount);
    }

    @RequestMapping("/deleteLike")
    public String deleteLike(@RequestParam("pb_one_id") String pbId, @RequestParam("user_account") String userAccount) {
        return likeService.deleteLike(pbId, userAccount);
    }
}
