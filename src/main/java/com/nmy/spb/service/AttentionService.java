package com.nmy.spb.service;

import java.util.Map;

/**
 * @author nmy
 * @title: AttentionService
 * @date 2022-01-22 18:11
 */
public interface AttentionService {

    String addAttentionTopic(Map<String, String> params);

    String queryAttentionTopic(String account);

    String deleteAttentionTopicById(String id, String userAccount);

}
