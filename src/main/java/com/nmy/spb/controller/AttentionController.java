package com.nmy.spb.controller;

import com.nmy.spb.service.AttentionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class AttentionController {

    @Resource
    AttentionService attentionService;

    @PostMapping("/addAttentionTopic")
    public String addAttentionTopic(@RequestParam Map<String, String> params) {
        return attentionService.addAttentionTopic(params);
    }

    @PostMapping("/queryAttentionTopic")
    public String queryAttentionTopic(@RequestParam(value = "user_account") String account) {
        return attentionService.queryAttentionTopic(account);
    }

    @PostMapping("/deleteAttentionTopicById")
    public String deleteAttentionTopicById(@RequestParam(value = "topic_id") String id, @RequestParam(value = "user_account") String userAccount) {
        return attentionService.deleteAttentionTopicById(id, userAccount);
    }


}
