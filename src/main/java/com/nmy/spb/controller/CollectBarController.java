package com.nmy.spb.controller;

import com.nmy.spb.domain.dto.CollectBarDto;
import com.nmy.spb.service.CollectBarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: CollectBarController
 * @date 2022-01-27 11:49
 */
@Controller
@ResponseBody
@RequestMapping("/collectbar")
public class CollectBarController {

    @Resource
    CollectBarService collectBarService;

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&String(pb_one_id)
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 19:58
     */
    @RequestMapping("/queryCollectBarList")
    public String queryCollectBarList(@RequestParam("user_account") String userAccount) {
        return collectBarService.queryCollectBarList(userAccount);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&BarDto
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 19:59
     */
    @RequestMapping("/queryCollectBarFullList")
    public String queryCollectBarFullList(@RequestParam("user_account") String userAccount) {
        return collectBarService.queryCollectBarFullList(userAccount);
    }

    /**
     * @Description: 必要数据：CollectBarDto -> pb_one_id,user_account
     * 返回：RequestEntityJson -> 状态码&String(ip)  |  RequestJson -> 状态码
     * @Param: [collectBarDto]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 20:00
     */
    /**
     * @Description: 必要数据：CollectBarDto -> pb_one_id,user_account
     * 非必要数据：cache_account
     * 返回：RequestEntityJson -> 状态码&String(ip)  |  RequestJson -> 状态码
     * 特殊：cache_account->判断是否发送通知(若收藏自己的帖子该值可置空)   客户端使用RequestEntityJson接收
     * @Param: [collectBarDto, cacheAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 20:35
     */
    @RequestMapping("/addCollectBar")
    public String addCollectBar(CollectBarDto collectBarDto,@RequestParam("cache_account") String cacheAccount) {
        return collectBarService.addCollectBar(collectBarDto,cacheAccount);
    }

    /**
     * @Description: 必要数据：CollectBarDto -> pb_one_id,user_account
     * 返回：RequestJson -> 状态码
     * @Param: [collectBarDto]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 20:00
     */
    @RequestMapping("/deleteCollectBar")
    public String deleteCollectBar(CollectBarDto collectBarDto) {
        return collectBarService.deleteCollectBar(collectBarDto);
    }

}
