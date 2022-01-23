package com.nmy.spb.controller;

import com.nmy.spb.service.SignService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: SignController
 * @date 2022-01-23 12:18
 */
@Controller
@ResponseBody
@RequestMapping("/sign")
public class SignController {

    @Resource
    SignService signService;

    @RequestMapping("/queryUserSign")
    public String queryUserSign(@RequestParam("user_account") String userAccount) {
        return signService.queryUserSign(userAccount);
    }

    @RequestMapping("/updateSignDay")
    public String updateSignDay(@RequestParam("user_account") String userAccount) {
        return signService.updateSignDay(userAccount);
    }

    @RequestMapping("/updateSignRight")
    public String updateSignRight(@RequestParam("user_account") String userAccount) {
        return signService.updateSignRight(userAccount);
    }

    @RequestMapping("/updateSignDayAndRight")
    public String updateSignDayAndRight(@RequestParam("user_account") String userAccount) {
        return signService.updateSignDayAndRight(userAccount);
    }

    @RequestMapping("/updateSignDayAndRightAndCoin")
    public String updateSignDayAndRightAndCoin(@RequestParam("user_account") String userAccount, @RequestParam("sign_day") String signDay, @RequestParam("coin") String coin) {
        return signService.updateSignDayAndRightAndCoin(userAccount, signDay, coin);
    }

    @RequestMapping("/updateSignCoin")
    public String updateSignCoin(@RequestParam("user_account") String userAccount, @RequestParam("coin") String coin) {
        return signService.updateSignCoin(userAccount, coin);
    }

    @RequestMapping("/updateSignStarBadge")
    public String updateSignStarBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_star_badge") String starBadge) {
        return signService.updateSignStarBadge(userAccount, starBadge);
    }

    @RequestMapping("/updateSignLikeBadge")
    public String updateSignLikeBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_like_badge") String likeBadge) {
        return signService.updateSignLikeBadge(userAccount, likeBadge);
    }

    @RequestMapping("/updateSignTaskBadge")
    public String updateSignTaskBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_tesk_badge") String taskBadge) {
        return signService.updateSignTaskBadge(userAccount, taskBadge);
    }


}
