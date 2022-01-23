package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.mapper.TopicMapper;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: TopicServiceImpl
 * @date 2022-01-23 14:11
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    TopicMapper topicMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryTopicNameList() {
        return sqlResultService.process(topicMapper.queryTopicNameList());
    }

    @Override
    public String querySearchTopicNameList(String topicName) {
        return sqlResultService.process(topicMapper.querySearchTopicNameList(topicName));
    }

    @Override
    public String queryRundomTopicFullList() {
        int min = (int) (Math.random() * 200)  + 100;
        int max = (int) (Math.random() * 500)  + 500;
        return sqlResultService.process(topicMapper.queryRundomTopicFullList(min,max));
    }

    @Override
    public String queryTopicFull(String topicName) {
        return sqlResultService.process(topicMapper.queryTopicFull(topicName));
    }

    @Override
    public String querySearchTopicFullList(String topicName) {
        return sqlResultService.process(topicMapper.querySearchTopicFullList(topicName));
    }

    @Override
    public String updateTopicIncreaseAttention(String id) {
        return sqlResultService.noProcess(topicMapper.updateTopicIncreaseAttention(Integer.parseInt(id)));
    }

    @Override
    public String updateTopicDecreaseAttention(String id) {
        return sqlResultService.noProcess(topicMapper.updateTopicDecreaseAttention(Integer.parseInt(id)));
    }
}
