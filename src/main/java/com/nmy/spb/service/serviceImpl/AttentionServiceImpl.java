package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.AttentionTopicDto;
import com.nmy.spb.service.AttentionService;
import com.nmy.spb.utils.DataVerificationTool;
import com.soft.spb.pojo.dto.AttentiontopicDto;
import com.soft.spb.pojo.entity.Attentiontopic;
import com.soft.spb.pojo.vo.AttentiontopicVo;
import com.soft.spb.service.AttentiontopicService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    SqlResultServiceImpl sqlResultService;

    @DubboReference
    AttentiontopicService attentiontopicService;

    @Override
    public String addAttentionTopic(Map<String, String> params) {
        String userAccount = params.get("user_account");
        String topicId = params.get("topic_id");
        String topicName = params.get("topic_name");

        if (DataVerificationTool.isEmpty(userAccount, topicId, topicName)) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        Attentiontopic attentiontopic = new Attentiontopic();
        attentiontopic.setUserAccount(userAccount);
        attentiontopic.setTopicId(Integer.parseInt(topicId));
        attentiontopic.setTopicName(topicName);
        Boolean aBoolean = attentiontopicService.addAttentionTopic(attentiontopic);
        if (aBoolean) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_ADD_ATTENTONTOPIC);
        } else {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }

    @Override
    public String queryAttentionTopic(String account) {
        AttentiontopicDto attentiontopicDto = new AttentiontopicDto();
        attentiontopicDto.setUserAccount(account);
        attentiontopicDto.setSearch("#");
        attentiontopicDto.setId(0L);
        List<AttentiontopicVo> attentiontopicVos = attentiontopicService.queryAttentionTopic(attentiontopicDto);
        ArrayList<AttentionTopicDto> ats = new ArrayList<>();
        for (AttentiontopicVo o : attentiontopicVos) {
            AttentionTopicDto attentionTopicDto = new AttentionTopicDto();
            attentionTopicDto.setId(o.getId());
            attentionTopicDto.setTopic_attentionnum(o.getTopicAttentionnum());
            attentionTopicDto.setTopic_barnum(o.getTopicBarnum());
            attentionTopicDto.setTopic_date(o.getTopicDate().toString());
            attentionTopicDto.setTopic_image(o.getTopicImage());
            attentionTopicDto.setTopic_name(o.getTopicName());
            attentionTopicDto.setTopic_slogan(o.getTopicSlogan());
            ats.add(0, attentionTopicDto);
        }
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, ats));
    }

    @Override
    public String deleteAttentionTopicById(String id, String userAccount) {
        Attentiontopic attentiontopic = new Attentiontopic();
        attentiontopic.setUserAccount(userAccount);
        attentiontopic.setTopicId(Integer.parseInt(id));
        Boolean aBoolean = attentiontopicService.deleteAttentionTopicById(attentiontopic);
        if (aBoolean) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_ATTENTONTOPIC);
        } else {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }
}
