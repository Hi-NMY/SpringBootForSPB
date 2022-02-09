package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.BarDto;
import com.nmy.spb.domain.dto.CollectBarDto;
import com.nmy.spb.mapper.CollectBarMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.CollectBarService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DataVerificationTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: CollectBarServiceImpl
 * @date 2022-01-27 19:14
 */
@Service
public class CollectBarServiceImpl implements CollectBarService {

    @Resource
    CollectBarMapper collectBarMapper;

    @Resource
    UserIpMapper userIpMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryCollectBarList(String userAccount) {
        List<String> barIdList = collectBarMapper.queryCollectBarList(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barIdList));
    }

    @Override
    public String queryCollectBarFullList(String userAccount) {
        List<BarDto> barDtos = collectBarMapper.queryCollectBarFullList(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String addCollectBar(CollectBarDto collectBarDto, String cacheAccount) {
        String pbOneId = collectBarDto.getPb_one_id();
        String userAccount = collectBarDto.getUser_account();
        if (DataVerificationTool.isEmpty(pbOneId, userAccount)) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }

        int value = collectBarMapper.addCollectBar(userAccount, pbOneId);
        if (value == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        if (DataVerificationTool.isEmpty(cacheAccount)){
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

        int value = collectBarMapper.deleteCollectBar(userAccount, pbOneId);
        if (value == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
    }
}
