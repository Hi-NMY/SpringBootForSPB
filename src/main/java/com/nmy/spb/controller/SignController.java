package com.nmy.spb.controller;

import com.nmy.spb.service.SignService;
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

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestEntityJson -> 状态码&Sign
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/queryUserSign")
    public String queryUserSign(@RequestParam("user_account") String userAccount) {
        return signService.queryUserSign(userAccount);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/updateSignDay")
    public String updateSignDay(@RequestParam("user_account") String userAccount) {
        return signService.updateSignDay(userAccount);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/updateSignRight")
    public String updateSignRight(@RequestParam("user_account") String userAccount) {
        return signService.updateSignRight(userAccount);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/updateSignDayAndRight")
    public String updateSignDayAndRight(@RequestParam("user_account") String userAccount) {
        return signService.updateSignDayAndRight(userAccount);
    }

    /**
     * @Description: 必要数据：user_account,sign_day,coin
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/updateSignDayAndRightAndCoin")
    public String updateSignDayAndRightAndCoin(@RequestParam("user_account") String userAccount, @RequestParam("sign_day") String signDay, @RequestParam("coin") String coin) {
        return signService.updateSignDayAndRightAndCoin(userAccount, signDay, coin);
    }

    /**
     * @Description: 必要数据：user_account,coin
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/updateSignCoin")
    public String updateSignCoin(@RequestParam("user_account") String userAccount, @RequestParam("coin") String coin) {
        return signService.updateSignCoin(userAccount, coin);
    }

    /**
     * @Description: 必要数据：user_account,sign_star_badge
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/updateSignStarBadge")
    public String updateSignStarBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_star_badge") String starBadge) {
        return signService.updateSignStarBadge(userAccount, starBadge);
    }

    /**
     * @Description: 必要数据：user_account,sign_like_badge
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/updateSignLikeBadge")
    public String updateSignLikeBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_like_badge") String likeBadge) {
        return signService.updateSignLikeBadge(userAccount, likeBadge);
    }

    /**
     * @Description: 必要数据：user_account,sign_tesk_badge
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 20:57
     */
    @RequestMapping("/updateSignTaskBadge")
    public String updateSignTaskBadge(@RequestParam("user_account") String userAccount, @RequestParam("sign_tesk_badge") String taskBadge) {
        return signService.updateSignTaskBadge(userAccount, taskBadge);
    }


}
