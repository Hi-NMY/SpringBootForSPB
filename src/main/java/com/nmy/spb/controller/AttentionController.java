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

    /**
     * @Description: Map
     * 必要数据：user_account，topic_id，topic_name
     * 返回：RequestCode -> 状态码
     * @Param: [params]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 0:11
     */
    @PostMapping("/addAttentionTopic")
    public String addAttentionTopic(@RequestParam Map<String, String> params) {
        return attentionService.addAttentionTopic(params);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&AttentionTopicDto
     * @Param: [account]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 0:13
     */
    @PostMapping("/queryAttentionTopic")
    public String queryAttentionTopic(@RequestParam("user_account") String account) {
        return attentionService.queryAttentionTopic(account);
    }

    /**
     * @Description: 必要数据：topic_id，user_account
     * 返回：RequestCode -> 状态码
     * @Param: [id, userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-26 0:14
     */
    @PostMapping("/deleteAttentionTopicById")
    public String deleteAttentionTopicById(@RequestParam("topic_id") String id, @RequestParam("user_account") String userAccount) {
        return attentionService.deleteAttentionTopicById(id, userAccount);
    }


}
