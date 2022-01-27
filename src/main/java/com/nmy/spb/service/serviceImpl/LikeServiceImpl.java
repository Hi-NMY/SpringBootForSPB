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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: LikeServiceImpl
 * @date 2022-01-23 11:46
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    LikeMapper likeMapper;

    @Resource
    UserIpMapper userIpMapper;

    @Resource
    PostBarMapper postBarMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryLike(String userAccount) {
        List<Like> likes = likeMapper.queryLike(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, likes));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addLike(String pbId, String userAccount, String cacheAccount) {
        try {
            int bi = likeMapper.addLike(userAccount, pbId);
            int ai = postBarMapper.updateIncreaseLike(pbId);
            if (!sqlResultService.transactionalProcess(ai, bi)) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }

            if (!DataVerificationTool.isEmpty(cacheAccount)) {
                String userIp = userIpMapper.queryUserIp(cacheAccount);
                return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, userIp));
            }
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteLike(String pbId, String userAccount) {
        try {
            int bi = likeMapper.deleteLike(userAccount, pbId);
            int ai = postBarMapper.updateReduceLike(pbId);
            if (sqlResultService.transactionalProcess(ai, bi)) {
                return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }
}
