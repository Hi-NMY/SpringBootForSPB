package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.RequestResultCode;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.mapper.SignMapper;
import com.nmy.spb.mapper.UserMapper;
import com.nmy.spb.service.SignService;
import com.nmy.spb.service.SqlResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: SignServiceImpl
 * @date 2022-01-23 13:19
 */
@Service
public class SignServiceImpl implements SignService {

    @Resource
    SignMapper signMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryUserSign(String userAccount) {
        return sqlResultService.process(signMapper.queryUserSign(userAccount));
    }

    @Override
    public String updateSignDay(String userAccount) {
        return sqlResultService.noProcess(signMapper.updateSignDay(userAccount));
    }

    @Override
    public String updateSignRight(String userAccount) {
        return sqlResultService.noProcess(signMapper.updateSignRight(userAccount));
    }

    @Override
    public String updateSignDayAndRight(String userAccount) {
        return sqlResultService.noProcess(signMapper.updateSignDayAndRight(userAccount));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateSignDayAndRightAndCoin(String userAccount, String signDay, String coin) {
        try {
            int ai = signMapper.updateSignDayAndRightAndCoin(userAccount, signDay, Integer.parseInt(coin));
            int bi = userMapper.updateUserLongDay(userAccount);
            if (sqlResultService.transactionalProcess(ai,bi)){
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

    @Override
    public String updateSignCoin(String userAccount, String coin) {
        return sqlResultService.noProcess(signMapper.updateSignCoin(userAccount,Integer.parseInt(coin)));
    }

    @Override
    public String updateSignStarBadge(String userAccount, String starBadge) {
        return sqlResultService.noProcess(signMapper.updateSignStarBadge(userAccount,starBadge));
    }

    @Override
    public String updateSignLikeBadge(String userAccount, String likeBadge) {
        return sqlResultService.noProcess(signMapper.updateSignLikeBadge(userAccount,likeBadge));
    }

    @Override
    public String updateSignTaskBadge(String userAccount, String taskBadge) {
        return sqlResultService.noProcess(signMapper.updateSignTaskBadge(userAccount,taskBadge));
    }
}
