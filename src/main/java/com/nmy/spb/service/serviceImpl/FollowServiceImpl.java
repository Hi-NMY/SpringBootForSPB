package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.FollowUserDto;
import com.nmy.spb.domain.pojo.Follow;
import com.nmy.spb.mapper.FollowMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.FollowService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DataVerificationTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: FollowServiceImpl
 * @date 2022-01-23 10:10
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Resource
    FollowMapper followMapper;

    @Resource
    SqlResultService sqlResultService;

    @Resource
    UserIpMapper userIpMapper;

    @Override
    public String queryFollowList(String userAccount) {
        List<String> follows = followMapper.queryFollowList(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, follows));
    }

    @Override
    public String queryFollowUserList(String userAccount) {
        List<FollowUserDto> followUserDtos = followMapper.queryFollowUserList(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, followUserDtos));
    }

    @Override
    public String addFollow(String followAccount, String followedAccount) {
        int value = followMapper.addFollow(followAccount, followedAccount);

        if (value == SQLResultCode.SUCCEES) {
            String ip = userIpMapper.queryUserIp(followedAccount);
            if (DataVerificationTool.isEmpty(ip)) {
                return sqlResultService.noProcess(EnumCode.SUCCESS_FOLLOW);
            }
            return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_FOLLOW, ip));
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }

    @Override
    public String deleteFollow(String followAccount, String followedAccount) {
        int value = followMapper.deleteFollow(followAccount, followedAccount);
        if (value == SQLResultCode.SUCCEES) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_FOLLOW);
        }

        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }
}
