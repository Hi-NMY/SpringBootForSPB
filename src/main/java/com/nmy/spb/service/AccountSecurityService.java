package com.nmy.spb.service;

import com.nmy.spb.domain.dto.UpdatePasswordDto;
import com.nmy.spb.domain.dto.VerifyPasswordDto;

/**
 * @author nmy
 * @title: AccountSecurityService
 * @date 2022-01-23 17:20
 */
public interface AccountSecurityService {

    String updateUserPassword(UpdatePasswordDto updatePasswordDto);

    String queryVerifyAndUserFull(String userAccount);

    String queryVerifyUserPassword(VerifyPasswordDto verifyPasswordDto);
}
