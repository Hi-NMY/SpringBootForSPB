package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.pojo.Topic;
import com.nmy.spb.mapper.TopicMapper;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        List<String> strings = topicMapper.queryTopicNameList();
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, strings));
    }

    @Override
    public String querySearchTopicNameList(String topicName) {
        List<String> strings = topicMapper.querySearchTopicNameList(topicName);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, strings));
    }

    @Override
    public String queryRundomTopicFullList() {
        int min = (int) (Math.random() * 200) + 100;
        int max = (int) (Math.random() * 500) + 500;
        List<Topic> topics = topicMapper.queryRundomTopicFullList(min, max);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, topics));
    }

    @Override
    public String queryTopicFull(String topicName) {
        Topic topic = topicMapper.queryTopicFull(topicName);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, topic));
    }

    @Override
    public String querySearchTopicFullList(String topicName) {
        List<Topic> topics = topicMapper.querySearchTopicFullList(topicName);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, topics));
    }
}
