package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.domain.dto.UserRegisteredDto;
import com.nmy.spb.mapper.AccountSecurityMapper;
import com.nmy.spb.mapper.InitTableMapper;
import com.nmy.spb.mapper.UserMapper;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.service.UserRegisteredService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: UserRegisteredServiceImpl
 * @date 2022-01-23 21:05
 */
@Service
public class UserRegisteredServiceImpl implements UserRegisteredService {

    @Resource
    AccountSecurityMapper accountSecurityMapper;

    @Resource
    InitTableMapper initTableMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    SqlResultService sqlResultService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String userRegistered(UserRegisteredDto userRegisteredDto, MultipartFile file) {
     //   File f = new File();
        return null;
    }
}
