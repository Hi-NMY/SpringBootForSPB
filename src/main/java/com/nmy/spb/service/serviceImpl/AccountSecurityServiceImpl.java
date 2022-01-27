package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.UpdatePasswordDto;
import com.nmy.spb.domain.dto.UserDto;
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
        String account = updatePasswordDto.getUser_account();
        String passwordOld = updatePasswordDto.getUser_password_old();
        String password = updatePasswordDto.getUser_password();
        int value = accountSecurityMapper.queryUserExist(account, passwordOld);

        if (value != SQLResultCode.ERROR) {
            int i = accountSecurityMapper.updateUserPassword(account, password);
            if (i != SQLResultCode.ERROR) {
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
            return sqlResultService.noProcess(EnumCode.SUCCESS_UPDATA_PASSWORD);
        }

        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }

    @Override
    public String queryVerifyAndUserFull(String userAccount) {
        UserDto userDto = accountSecurityMapper.queryUserFull(userAccount);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, userDto));
    }

    @Override
    public String queryVerifyUserPassword(VerifyPasswordDto verifyPasswordDto) {
        String account = verifyPasswordDto.getUser_account();
        String password = verifyPasswordDto.getUser_password();
        String ip = verifyPasswordDto.getUser_ip();
        int value = accountSecurityMapper.queryUserExist(account, password);
        if (value != SQLResultCode.ERROR) {
            int i = userIpMapper.updateUserIp(account, ip);
            if (i != SQLResultCode.ERROR) {
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
            return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
        }

        return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }
}
