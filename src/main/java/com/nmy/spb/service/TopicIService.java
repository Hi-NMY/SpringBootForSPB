package com.nmy.spb.service;

/**
 * @author nmy
 * @title: TopicIService
 * @date 2022-01-23 14:09
 */
public interface TopicIService {

    String queryTopicNameList();

    String querySearchTopicNameList(String topicName);

    String queryRundomTopicFullList();

    String queryTopicFull(String topicName);

    String querySearchTopicFullList(String topicName);
}
