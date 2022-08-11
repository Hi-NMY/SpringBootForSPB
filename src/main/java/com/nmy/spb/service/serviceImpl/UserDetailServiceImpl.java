package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.domain.dto.SUserDetails;
import com.nmy.spb.domain.pojo.Users;
import com.nmy.spb.mapper.AccountSecurityMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: UserDetailServiceImpl
 * @date 2022-08-06 13:25
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    AccountSecurityMapper accountSecurityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //传入用户名
        //数据库查找该用户
        Users users = accountSecurityMapper.queryUserExist(username);
        if (users != null) {
            return new SUserDetails(users);
        } else {
            return null;
        }
    }
}
