package com.nmy.spb.controller;

import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.service.TopicService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: TopicController
 * @date 2022-01-23 14:08
 */
@Controller
@ResponseBody
@RequestMapping("/topic")
@Api(tags = "话题控制")
public class TopicController {

    @Resource
    TopicService topicService;

    @RequestMapping(path = "/queryTopicNameList", method = RequestMethod.POST)
    @ApiOperation(value = "获取话题名列表", notes = "RequestListJson -> 状态码&String(topic_name)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryTopicNameList() {
        return topicService.queryTopicNameList();
    }

    @RequestMapping(path = "/querySearchTopicNameList", method = RequestMethod.POST)
    @ApiOperation(value = "搜索话题名列表", notes = "RequestListJson -> 状态码&String(topic_name)\n" +
            "搜索仅展示话题名字")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic_name", value = "话题名", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String querySearchTopicNameList(@RequestParam("topic_name") String topicName) {
        return topicService.querySearchTopicNameList(topicName);
    }

    @RequestMapping(path = "/queryRundomTopicFullList", method = RequestMethod.GET)
    @ApiOperation(value = "获取随机话题列表", notes = "状态码&Topic")
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryRundomTopicFullList() {
        return topicService.queryRundomTopicFullList();
    }

    @RequestMapping(path = "/queryTopicFull", method = RequestMethod.POST)
    @ApiOperation(value = "获取话题详细信息", notes = "RequestEntityJson -> 状态码&Topic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic_name", value = "话题名", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryTopicFull(@RequestParam("topic_name") String topicName) {
        return topicService.queryTopicFull(topicName);
    }

    @RequestMapping(path = "/querySearchTopicFullList", method = RequestMethod.POST)
    @ApiOperation(value = "获取搜索话题详细列表", notes = "RequestListJson -> 状态码&Topic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic_name", value = "话题名", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String querySearchTopicFullList(@RequestParam("topic_name") String topicName) {
        return topicService.querySearchTopicFullList(topicName);
    }

}
