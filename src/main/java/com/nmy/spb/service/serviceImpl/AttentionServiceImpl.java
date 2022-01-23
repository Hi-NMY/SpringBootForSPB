package com.nmy.spb.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmy.spb.common.RequestResultCode;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.AttentionTopicDto;
import com.nmy.spb.mapper.AttentionTopicMapper;
import com.nmy.spb.service.AttentionService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DatabasesTableNameTool;
import com.nmy.spb.utils.DateTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author nmy
 * @title: AttentionServiceImpl
 * @date 2022-01-22 18:12
 */
@Service
public class AttentionServiceImpl implements AttentionService {

    @Resource
    AttentionTopicMapper attentionTopicMapper;
    @Resource
    SqlResultServiceImpl sqlResultService;

    @Override
    public String addAttentionTopic(Map<String, String> params) {
        String account = params.get("user_account");
        int value = attentionTopicMapper.addAttentionTopic(DatabasesTableNameTool.getAttentionTopicName(account)
                , params.get("topic_id"), params.get("topic_name"), DateTool.obtainNowDateTime());

        return sqlResultService.noProcess(value);
    }

    @Override
    public String queryAttentionTopic(String account) {
        List<AttentionTopicDto> attentionTopicDtos = attentionTopicMapper.queryAttentionTopic(DatabasesTableNameTool.getAttentionTopicName(account)
                , DateTool.obtainNowDateTime());

        return sqlResultService.process(attentionTopicDtos);
    }

    @Override
    public String deleteAttentionTopicById(String id, String userAccount) {
        int value = attentionTopicMapper.deleteAttentionTopicById(DatabasesTableNameTool.getAttentionTopicName(userAccount), id);
        return sqlResultService.noProcess(value);
    }
}
