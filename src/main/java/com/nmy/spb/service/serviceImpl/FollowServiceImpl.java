package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.FollowUserDto;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.FollowIService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DataVerificationTool;
import com.soft.spb.pojo.dto.FollowDto;
import com.soft.spb.pojo.entity.Follow;
import com.soft.spb.pojo.vo.FollowListVo;
import com.soft.spb.service.FollowService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nmy
 * @title: FollowServiceImpl
 * @date 2022-01-23 10:10
 */
@Service
public class FollowServiceImpl implements FollowIService {

    @Resource
    SqlResultService sqlResultService;

    @Resource
    UserIpMapper userIpMapper;

    @DubboReference
    FollowService followService;

    @Override
    public String queryFollowList(String userAccount) {
        Follow follow = new Follow();
        follow.setFollowedAccount(userAccount);
        List<String> strings = followService.queryFollowedList(follow);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, strings));
    }

    @Override
    public String queryFollowCount(String userAccount) {
        Follow follow = new Follow();
        follow.setFollowAccount(userAccount);
        Integer integer = followService.queryFollowAccount(follow);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, integer));
    }

    @Override
    public String queryFollowedCount(String userAccount) {
        Follow follow = new Follow();
        follow.setFollowedAccount(userAccount);
        Integer integer = followService.queryFollowedCount(follow);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, integer));
    }

    @Override
    public String queryFollowUserList(String userAccount) {
        FollowDto followDto = new FollowDto();
        followDto.setUserAccount(userAccount);
        followDto.setId(0L);
        List<FollowListVo> followListVos = followService.queryFollowUserList(followDto);
        ArrayList<FollowUserDto> fol = new ArrayList<>();
        for (FollowListVo vos : followListVos) {
            FollowUserDto followUserDto = new FollowUserDto();
            followUserDto.setId(Math.toIntExact(vos.getId()));
            followUserDto.setStu_sex(vos.getSex());
            followUserDto.setUser_account(vos.getUserAccount());
            followUserDto.setUser_badge(vos.getUserBadge());
            followUserDto.setUser_name(vos.getUserName());
            fol.add(followUserDto);
        }
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, fol));
    }

    @Override
    public String addFollow(String followAccount, String followedAccount) {
        Follow follow = new Follow();
        follow.setFollowAccount(followAccount);
        follow.setFollowedAccount(followedAccount);
        boolean followed = followService.isFollowed(follow);
        if (followed) {
            String ip = userIpMapper.queryUserIp(followedAccount);
            if (DataVerificationTool.isEmpty(ip)) {
                return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_FOLLOW, ""));
            }
            return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_FOLLOW, ip));
        }
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.ERROR_DEFAULT, ""));
    }

    @Override
    public String deleteFollow(String followAccount, String followedAccount) {
        Follow follow = new Follow();
        follow.setFollowAccount(followAccount);
        follow.setFollowedAccount(followedAccount);
        boolean b = followService.deleteFollow(follow);
        if (b) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_FOLLOW);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }
}
