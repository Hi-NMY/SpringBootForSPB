package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.RequestResultCode;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.pojo.Follow;
import com.nmy.spb.mapper.FollowMapper;
import com.nmy.spb.mapper.FollowedMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.FollowService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DatabasesTableNameTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    FollowedMapper followedMapper;

    @Resource
    SqlResultService sqlResultService;

    @Resource
    UserIpMapper userIpMapper;

    @Override
    public String queryFollowList(String userAccount) {
        return sqlResultService.process(followMapper.queryFollowList(DatabasesTableNameTool.getFollowName(userAccount)));
    }

    @Override
    public String queryFollowUserList(String userAccount) {
        return sqlResultService.process(followMapper.queryFollowUserList(DatabasesTableNameTool.getFollowName(userAccount)));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addFollow(String cacheAccount, String userAccount) {
        try {
            int ai = followMapper.addFollow(DatabasesTableNameTool.getFollowName(userAccount), cacheAccount);
            int bi = followedMapper.addFollowed(DatabasesTableNameTool.getFollowedName(cacheAccount), userAccount);

            if (ai == SQLResultCode.SUCCEES && bi == SQLResultCode.SUCCEES){
                String ip = userIpMapper.queryUserIp(cacheAccount);
                return RequestResultCode.SUCCEES + ip;
            }else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RequestResultCode.ERROR;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RequestResultCode.ERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteFollow(String cacheAccount, String userAccount) {
        try {
            int ai = followMapper.deleteFollow(DatabasesTableNameTool.getFollowName(userAccount), cacheAccount);
            int bi = followedMapper.deleteFollowed(DatabasesTableNameTool.getFollowedName(cacheAccount), userAccount);
            if (ai == SQLResultCode.SUCCEES && bi == SQLResultCode.SUCCEES){
                return RequestResultCode.SUCCEES;
            }else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RequestResultCode.ERROR;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RequestResultCode.ERROR;
        }
    }
}
