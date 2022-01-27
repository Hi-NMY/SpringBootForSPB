package com.nmy.spb.controller;

import com.nmy.spb.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class TopicController {

    @Resource
    TopicService topicService;

    /**
     * @Description: 必要数据：无
     * 返回：RequestListJson -> 状态码&String(topic_name)
     * @Param: []
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 21:21
     */
    @RequestMapping("/queryTopicNameList")
    public String queryTopicNameList() {
        return topicService.queryTopicNameList();
    }

    /**
     * @Description: 必要数据：topic_name
     * 返回：RequestListJson -> 状态码&String(topic_name)
     * 特殊：搜索展示话题名字
     * @Param: []
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 21:21
     */
    @RequestMapping("/querySearchTopicNameList")
    public String querySearchTopicNameList(@RequestParam("topic_name") String topicName) {
        return topicService.querySearchTopicNameList(topicName);
    }

    /**
     * @Description: 必要数据：无
     * 返回：RequestListJson -> 状态码&Topic
     * @Param: []
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 21:21
     */
    @RequestMapping("/queryRundomTopicFullList")
    public String queryRundomTopicFullList() {
        return topicService.queryRundomTopicFullList();
    }

    /**
     * @Description: 必要数据：topic_name
     * 返回：RequestEntityJson -> 状态码&Topic
     * @Param: []
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 21:21
     */
    @RequestMapping("/queryTopicFull")
    public String queryTopicFull(@RequestParam("topic_name") String topicName) {
        return topicService.queryTopicFull(topicName);
    }

    /**
     * @Description: 必要数据：topic_name
     * 返回：RequestListJson -> 状态码&Topic
     * @Param: []
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 21:21
     */
    @RequestMapping("/querySearchTopicFullList")
    public String querySearchTopicFullList(@RequestParam("topic_name") String topicName) {
        return topicService.querySearchTopicFullList(topicName);
    }

}
