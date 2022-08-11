package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.service.AttentionService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author nmy
 * @title: AttentionController
 * @date 2022-01-22 18:03
 */
@Controller
@ResponseBody
@RequestMapping("/attention")
@Api(tags = "话题关注控制")
public class AttentionController {

    @Resource
    AttentionService attentionService;

    @RequestMapping(path = "/addAttentionTopic", method = RequestMethod.POST)
    @ApiOperation(value = "添加关注话题", notes = "Boot使用Map解析\n" +
            "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "topic_id", value = "话题id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "topic_name", value = "话题名", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已关注话题", response = RequestCode.class)
    })
    public String addAttentionTopic(@ApiParam(hidden = true) @RequestParam Map<String, String> params) {
        return attentionService.addAttentionTopic(params);
    }

    @RequestMapping(path = "/queryAttentionTopic", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户关注话题", notes = "RequestListJson -> 状态码&AttentionTopicDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "该账号未注册", response = RequestListJson.class),
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryAttentionTopic(@RequestParam("user_account") String account) {
        return attentionService.queryAttentionTopic(account);
    }

    @RequestMapping(path = "/deleteAttentionTopicById", method = RequestMethod.POST)
    @ApiOperation(value = "删除用户关注话题", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic_id", value = "话题id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "取消关注", response = RequestCode.class)
    })
    public String deleteAttentionTopicById(@RequestParam("topic_id") String id, @RequestParam("user_account") String userAccount) {
        return attentionService.deleteAttentionTopicById(id, userAccount);
    }
}
