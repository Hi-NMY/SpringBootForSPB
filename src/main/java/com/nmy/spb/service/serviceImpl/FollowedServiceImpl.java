package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.dto.FollowUserDto;
import com.nmy.spb.mapper.FollowMapper;
import com.nmy.spb.service.FollowedService;
import com.nmy.spb.service.SqlResultService;
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
 * @title: FollowedServiceImpl
 * @date 2022-01-23 11:35
 */
@Service
public class FollowedServiceImpl implements FollowedService {

    @Resource
    SqlResultService sqlResultService;

    @DubboReference
    FollowService followService;

    @Override
    public String queryFollowedList(String userAccount) {
        Follow follow = new Follow();
        follow.setFollowAccount(userAccount);
        List<String> strings = followService.queryFollowList(follow);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, strings));
    }

    @Override
    public String queryFollowedUserList(String userAccount) {
        FollowDto followDto = new FollowDto();
        followDto.setUserAccount(userAccount);
        followDto.setId(0L);
        List<FollowListVo> followListVos = followService.queryFollowedUserList(followDto);
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
}
