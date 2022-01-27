package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.pojo.Sign;
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
        Sign sign = signMapper.queryUserSign(userAccount);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, sign));
    }

    @Override
    public String updateSignDay(String userAccount) {
        int value = signMapper.updateSignDay(userAccount);
        if (value == SQLResultCode.SUCCEES) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }

    @Override
    public String updateSignRight(String userAccount) {
        int value = signMapper.updateSignRight(userAccount);
        if (value == SQLResultCode.SUCCEES) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }

    @Override
    public String updateSignDayAndRight(String userAccount) {
        int value = signMapper.updateSignDayAndRight(userAccount);
        if (value == SQLResultCode.SUCCEES) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateSignDayAndRightAndCoin(String userAccount, String signDay, String coin) {
        try {
            int ai = signMapper.updateSignDayAndRightAndCoin(userAccount, signDay, Integer.parseInt(coin));
            int bi = userMapper.updateUserLongDay(userAccount);
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

    @Override
    public String updateSignCoin(String userAccount, String coin) {
        int value = signMapper.updateSignCoin(userAccount, Integer.parseInt(coin));
        if (value == SQLResultCode.SUCCEES) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }

    @Override
    public String updateSignStarBadge(String userAccount, String starBadge) {
        int value = signMapper.updateSignStarBadge(userAccount, starBadge);
        if (value == SQLResultCode.SUCCEES) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }

    @Override
    public String updateSignLikeBadge(String userAccount, String likeBadge) {
        int value = signMapper.updateSignLikeBadge(userAccount, likeBadge);
        if (value == SQLResultCode.SUCCEES) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }

    @Override
    public String updateSignTaskBadge(String userAccount, String taskBadge) {
        int value = signMapper.updateSignTaskBadge(userAccount, taskBadge);
        if (value == SQLResultCode.SUCCEES) {
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }
}
