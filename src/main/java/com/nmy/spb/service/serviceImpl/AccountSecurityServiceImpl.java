package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.SUserDetails;
import com.nmy.spb.domain.dto.UpdatePasswordDto;
import com.nmy.spb.domain.dto.UserDto;
import com.nmy.spb.domain.dto.VerifyPasswordDto;
import com.nmy.spb.domain.pojo.Users;
import com.nmy.spb.mapper.AccountSecurityMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.AccountSecurityService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.RedisUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PasswordEncoder pas;

    private final String secret = "${token.secret}";

    @Override
    public String updateUserPassword(UpdatePasswordDto updatePasswordDto) {
        Users users = accountSecurityMapper.queryUserExist(updatePasswordDto.getUser_account());
        if (users == null) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }

        if (pas.matches(updatePasswordDto.getUser_password_old(), users.getUser_password())) {
            int i = accountSecurityMapper.updateUserPassword(updatePasswordDto.getUser_account(), updatePasswordDto.getUser_password());
            if (i == 1) {
                redisUtil.deleteObject("login:app:" + updatePasswordDto.getUser_account());
                return sqlResultService.noProcess(EnumCode.SUCCESS_UPDATA_PASSWORD);
            } else {
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
        } else {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }

    @Override
    public String queryVerifyAndUserFull(String userAccount) {
        UserDto userDto = accountSecurityMapper.queryUserFull(userAccount);
        if (userDto == null) {
            return sqlResultService.process(new RequestEntityJson<>(EnumCode.ERROR_LogIn, null));
        }
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, userDto));
    }

    @Override
    public String queryVerifyUserPassword(VerifyPasswordDto verifyPasswordDto) {
        UsernamePasswordAuthenticationToken a = new UsernamePasswordAuthenticationToken(verifyPasswordDto.getUser_account(),
                verifyPasswordDto.getUser_password());
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(a);
        } catch (AuthenticationException e) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        int i = userIpMapper.updateUserIp(verifyPasswordDto.getUser_account(), verifyPasswordDto.getUser_ip());
        if (i == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        SUserDetails sUserDetails = (SUserDetails) authenticate.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("login_user_key", sUserDetails.getUsername());
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        redisUtil.setEasyObject("login:app:" + verifyPasswordDto.getUser_account(), sUserDetails);

        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, token));
    }

    @Override
    public String logout(VerifyPasswordDto verifyPasswordDto) {
        return redisUtil.deleteObject("login:app:" + verifyPasswordDto.getUser_account()) ?
                sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT) :
                sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
    }
}
