package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.RequestResultCode;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.UpdatePasswordDto;
import com.nmy.spb.domain.dto.VerifyPasswordDto;
import com.nmy.spb.mapper.AccountSecurityMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.AccountSecurityService;
import com.nmy.spb.service.SqlResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: AccountSecurityServiceImpl
 * @date 2022-01-23 17:39
 */
@Service
public class AccountSecurityServiceImpl implements AccountSecurityService {

    @Resource
    AccountSecurityMapper accountSecurityMapper;

    @Resource
    SqlResultService sqlResultService;

    @Resource
    UserIpMapper userIpMapper;

    @Override
    public String updateUserPassword(UpdatePasswordDto updatePasswordDto) {
        int value = accountSecurityMapper.queryUserExist(updatePasswordDto.getUser_account(), updatePasswordDto.getUser_password_old());

        if (value != SQLResultCode.ERROR) {
            return sqlResultService
                    .noProcess(accountSecurityMapper.updateUserPassword(updatePasswordDto.getUser_account(), updatePasswordDto.getUser_password()));
        }

        return RequestResultCode.ERROR;
    }

    @Override
    public String queryVerifyAndUserFull(String userAccount) {
        return sqlResultService.process(accountSecurityMapper.queryUserFull(userAccount));
    }

    @Override
    public String queryVerifyUserPassword(VerifyPasswordDto verifyPasswordDto) {
        int value = accountSecurityMapper.queryUserExist(verifyPasswordDto.getUser_account(), verifyPasswordDto.getUser_password());
        if (value != SQLResultCode.ERROR) {
            return sqlResultService.noProcess(userIpMapper.updateUserIp(verifyPasswordDto.getUser_account(), verifyPasswordDto.getUser_ip()));
        }
        return RequestResultCode.ERROR;
    }
}
