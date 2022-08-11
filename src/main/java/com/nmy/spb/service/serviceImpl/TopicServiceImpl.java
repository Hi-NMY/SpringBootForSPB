package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.pojo.Topic;
import com.nmy.spb.mapper.TopicMapper;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.service.TopicIService;
import com.soft.spb.pojo.dto.TopicInfoDto;
import com.soft.spb.service.TopicService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: TopicServiceImpl
 * @date 2022-01-23 14:11
 */
@Service
public class TopicServiceImpl implements TopicIService {

    @Resource
    TopicMapper topicMapper;

    @Resource
    SqlResultService sqlResultService;

    @DubboReference
    TopicService topicService;

    @Override
    public String queryTopicNameList() {
        List<String> strings = topicMapper.queryTopicNameList();
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, strings));
    }

    @Override
    public String querySearchTopicNameList(String topicName) {
        List<String> strings = topicService.querySearchTopicNameList(topicName);
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
        TopicInfoDto topicInfoDto = new TopicInfoDto();
        topicInfoDto.setTopicName(topicName);
        topicInfoDto.setTopicId(-1);
        com.soft.spb.pojo.entity.Topic topicFull = topicService.getTopicFull(topicInfoDto);
        Topic topic1 = new Topic();
        topic1.setId(topicFull.getId());
        topic1.setTopic_attentionnum(topicFull.getTopicAttentionnum());
        topic1.setTopic_barnum(topicFull.getTopicBarnum());
        topic1.setTopic_image(topicFull.getTopicImage());
        topic1.setTopic_name(topicFull.getTopicName());
        topic1.setTopic_slogan(topicFull.getTopicSlogan());
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, topic1));
    }

    @Override
    public String querySearchTopicFullList(String topicName) {
        List<Topic> topics = topicMapper.querySearchTopicFullList(topicName);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, topics));
    }
}
