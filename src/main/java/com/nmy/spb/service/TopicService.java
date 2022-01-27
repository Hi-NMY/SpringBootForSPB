package com.nmy.spb.service;

/**
 * @author nmy
 * @title: TopicService
 * @date 2022-01-23 14:09
 */
public interface TopicService {

    String queryTopicNameList();

    String querySearchTopicNameList(String topicName);

    String queryRundomTopicFullList();

    String queryTopicFull(String topicName);

    String querySearchTopicFullList(String topicName);
}
