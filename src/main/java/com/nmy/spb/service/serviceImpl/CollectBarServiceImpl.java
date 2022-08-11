package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.BarDto;
import com.nmy.spb.domain.dto.CollectBarDto;
import com.nmy.spb.mapper.CollectBarMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.CollectBarService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DataVerificationTool;
import com.soft.spb.pojo.entity.Collectbar;
import com.soft.spb.pojo.vo.PostbarlistVo;
import com.soft.spb.service.CollectbarService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nmy
 * @title: CollectBarServiceImpl
 * @date 2022-01-27 19:14
 */
@Service
public class CollectBarServiceImpl implements CollectBarService {

    @Resource
    UserIpMapper userIpMapper;

    @Resource
    SqlResultService sqlResultService;

    @DubboReference
    CollectbarService collectbarService;

    @Override
    public String queryCollectBarList(String userAccount) {
        List<String> collectBarPresenter = collectbarService.getCollectBarPresenter(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, collectBarPresenter));
    }

    @Override
    public String queryCollectBarFullList(String userAccount) {
        List<PostbarlistVo> postbarlistVos = collectbarService.queryCollectBarFullList(0L, userAccount);
        ArrayList<BarDto> barDtos = new ArrayList<>();
        for (PostbarlistVo vos : postbarlistVos) {
            BarDto bar = new BarDto();
            bar.setPb_article(vos.getPbArticle());
            bar.setPb_comment_num(vos.getPbCommentNum());
            bar.setPb_date(vos.getPbDate().toString());
            bar.setPb_image_url(vos.getPbImageUrl());
            bar.setPb_location(vos.getPbLocation());
            bar.setPb_one_id(vos.getPbOneId());
            bar.setPb_thumb_num(vos.getPbThumbNum());
            bar.setPb_topic(vos.getPbTopic());
            bar.setPb_video(vos.getPbVideo());
            bar.setPb_voice(vos.getPbVoice());
            bar.setUser_account(vos.getUserAccount());
            bar.setUser_badge(vos.getUserBadge());
            bar.setUser_name(vos.getUserName());
            barDtos.add(bar);
        }
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String addCollectBar(CollectBarDto collectBarDto, String cacheAccount) {
        String pbOneId = collectBarDto.getPb_one_id();
        String userAccount = collectBarDto.getUser_account();
        if (DataVerificationTool.isEmpty(pbOneId, userAccount)) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        Collectbar collectbar = new Collectbar();
        collectbar.setUserAccount(userAccount);
        collectbar.setPbOneId(pbOneId);
        boolean b = collectbarService.addCollectBar(collectbar);
        if (!b) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        if (DataVerificationTool.isEmpty(cacheAccount)) {
            return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_COLLECT, null));
        }

        String userIp = userIpMapper.queryUserIp(cacheAccount);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_COLLECT, userIp));
    }

    @Override
    public String deleteCollectBar(CollectBarDto collectBarDto) {
        String pbOneId = collectBarDto.getPb_one_id();
        String userAccount = collectBarDto.getUser_account();
        if (DataVerificationTool.isEmpty(pbOneId, userAccount)) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        Collectbar collectbar = new Collectbar();
        collectbar.setUserAccount(userAccount);
        collectbar.setPbOneId(pbOneId);
        boolean b = collectbarService.deleteCollectBar(collectbar);
        if (!b) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
    }
}
