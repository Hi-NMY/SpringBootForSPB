package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.FollowUserDto;
import com.nmy.spb.domain.pojo.Follow;
import com.nmy.spb.mapper.FollowMapper;
import com.nmy.spb.service.FollowedService;
import com.nmy.spb.service.SqlResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: FollowedServiceImpl
 * @date 2022-01-23 11:35
 */
@Service
public class FollowedServiceImpl implements FollowedService {

    @Resource
    FollowMapper followMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryFollowedList(String userAccount) {
        List<Follow> follows = followMapper.queryFollowedList(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, follows));
    }

    @Override
    public String queryFollowedUserList(String userAccount) {
        List<FollowUserDto> followUserDtos = followMapper.queryFollowedUserList(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, followUserDtos));
    }
}
