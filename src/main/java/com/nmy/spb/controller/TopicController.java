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

    @RequestMapping("/queryTopicNameList")
    public String queryTopicNameList() {
        return topicService.queryTopicNameList();
    }

    @RequestMapping("/querySearchTopicNameList")
    public String querySearchTopicNameList(@RequestParam("topic_name") String topicName) {
        return topicService.querySearchTopicNameList(topicName);
    }

    @RequestMapping("/queryRundomTopicFullList")
    public String queryRundomTopicFullList() {
        return topicService.queryRundomTopicFullList();
    }

    @RequestMapping("/queryTopicFull")
    public String queryTopicFull(@RequestParam("topic_name") String topicName) {
        return topicService.queryTopicFull(topicName);
    }

    @RequestMapping("/querySearchTopicFullList")
    public String querySearchTopicFullList(@RequestParam("topic_name") String topicName) {
        return topicService.querySearchTopicFullList(topicName);
    }

    @RequestMapping("/updateTopicIncreaseAttention")
    public String updateTopicIncreaseAttention(@RequestParam("topic_id") String id) {
        return topicService.updateTopicIncreaseAttention(id);
    }

    @RequestMapping("/updateTopicDecreaseAttention")
    public String updateTopicDecreaseAttention(@RequestParam("topic_id") String id) {
        return topicService.updateTopicDecreaseAttention(id);
    }


}
