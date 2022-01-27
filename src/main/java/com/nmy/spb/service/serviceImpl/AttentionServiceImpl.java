package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.AttentionTopicDto;
import com.nmy.spb.mapper.AttentionTopicMapper;
import com.nmy.spb.mapper.TopicMapper;
import com.nmy.spb.service.AttentionService;
import com.nmy.spb.utils.DataVerificationTool;
import com.nmy.spb.utils.DateTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    TopicMapper topicMapper;
    @Resource
    SqlResultServiceImpl sqlResultService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addAttentionTopic(Map<String, String> params) {
        String userAccount = params.get("user_account");
        String topicId = params.get("topic_id");
        String topicName = params.get("topic_name");

        if (DataVerificationTool.isEmpty(userAccount, topicId, topicName)) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        try {
            int ai = attentionTopicMapper.addAttentionTopic(userAccount, topicId, topicName, DateTool.obtainNowDateTime());
            int bi = topicMapper.updateIncreaseAttention(Integer.parseInt(topicId));
            if (sqlResultService.transactionalProcess(ai, bi)) {
                return sqlResultService.noProcess(EnumCode.SUCCESS_ADD_ATTENTONTOPIC);
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }

    @Override
    public String queryAttentionTopic(String account) {
        List<AttentionTopicDto> attentionTopicDtos = attentionTopicMapper.queryAttentionTopic(account, DateTool.obtainNowDateTime());

        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, attentionTopicDtos));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteAttentionTopicById(String id, String userAccount) {
        try {
            int ai = attentionTopicMapper.deleteAttentionTopicById(userAccount, id);
            int bi = topicMapper.updateReduceAttention(Integer.parseInt(id));
            if (sqlResultService.transactionalProcess(ai, bi)) {
                return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_ATTENTONTOPIC);
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }
}
