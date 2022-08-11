package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.pojo.Like;
import com.nmy.spb.mapper.LikeMapper;
import com.nmy.spb.mapper.PostBarMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.LikeService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DataVerificationTool;
import com.soft.spb.pojo.entity.Likepb;
import com.soft.spb.service.LikepbService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nmy
 * @title: LikeServiceImpl
 * @date 2022-01-23 11:46
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    UserIpMapper userIpMapper;

    @Resource
    SqlResultService sqlResultService;

    @DubboReference
    LikepbService likepbService;

    @Override
    public String queryLike(String userAccount) {
        Likepb likepb = new Likepb();
        likepb.setUserAccount(userAccount);
        List<String> strings = likepbService.queryLike(likepb);
        ArrayList<Like> likes = new ArrayList<>();
        for (String string : strings) {
            Like like = new Like();
            like.setPb_one_id(string);
            likes.add(like);
        }
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, likes));
    }

    @Override
    public String addLike(String pbId, String userAccount, String cacheAccount) {
        Likepb likepb = new Likepb();
        likepb.setPbOneId(pbId);
        likepb.setUserAccount(userAccount);
        boolean b = likepbService.addLike(likepb);
        if (b) {
            String userIp = userIpMapper.queryUserIp(cacheAccount);
            return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, userIp));
        }
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.ERROR_DEFAULT, null));
    }

    @Override
    public String deleteLike(String pbId, String userAccount) {
        Likepb likepb = new Likepb();
        likepb.setPbOneId(pbId);
        likepb.setUserAccount(userAccount);
        boolean b = likepbService.deleteLike(likepb);
        if (b) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }
}
