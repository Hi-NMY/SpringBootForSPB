package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.mapper.FollowedMapper;
import com.nmy.spb.service.FollowedService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DatabasesTableNameTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: FollowedServiceImpl
 * @date 2022-01-23 11:35
 */
@Service
public class FollowedServiceImpl implements FollowedService {

    @Resource
    FollowedMapper followedMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryFollowedList(String userAccount) {
        return sqlResultService.process(followedMapper.queryFollowedList(DatabasesTableNameTool.getFollowedName(userAccount)));
    }

    @Override
    public String queryFollowedUserList(String userAccount) {
        return sqlResultService.process(followedMapper.queryFollowedUserList(DatabasesTableNameTool.getFollowedName(userAccount)));
    }
}
