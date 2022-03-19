package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.CollectBarDto;
import com.nmy.spb.service.CollectBarService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "收藏帖子控制")
public class CollectBarController {

    @Resource
    CollectBarService collectBarService;

    @RequestMapping(path = "/queryCollectBarList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户收藏帖子(Basic)", notes = "RequestListJson -> 状态码&String(pb_one_id)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "被收藏用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryCollectBarList(@RequestParam("user_account") String userAccount) {
        return collectBarService.queryCollectBarList(userAccount);
    }

    @RequestMapping(path = "/queryCollectBarFullList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户收藏帖子(Full)", notes = "RequestListJson -> 状态码&BarDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryCollectBarFullList(@RequestParam("user_account") String userAccount) {
        return collectBarService.queryCollectBarFullList(userAccount);
    }

    @RequestMapping(path = "/addCollectBar", method = RequestMethod.POST)
    @ApiOperation(value = "添加收藏帖子", notes = "Boot使用CollectBarDto解析:\n" +
            "public String addCollectBar(CollectBarDto collectBarDto, @RequestParam(\"cache_account\") String cacheAccount)\n" +
            "RequestEntityJson -> 状态码&String(ip)\n" +
            "cache_account->判断是否发送通知(若收藏自己的帖子该值可置空)   客户端使用RequestEntityJson接收")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cache_account", value = "用户账号", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestEntityJson.class),
            @ApiResponse(code = 200, message = "已收藏", response = RequestEntityJson.class)
    })
    public String addCollectBar(CollectBarDto collectBarDto, @RequestParam("cache_account") String cacheAccount) {
        return collectBarService.addCollectBar(collectBarDto, cacheAccount);
    }

    @RequestMapping(path = "/deleteCollectBar", method = RequestMethod.POST)
    @ApiOperation(value = "删除收藏帖子", notes = "Boot使用CollectBarDto解析:\n" +
            "public String deleteCollectBar(CollectBarDto collectBarDto)\n" +
            "RequestCode -> 状态码")
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "空", response = RequestCode.class)
    })
    public String deleteCollectBar(CollectBarDto collectBarDto) {
        return collectBarService.deleteCollectBar(collectBarDto);
    }

}
