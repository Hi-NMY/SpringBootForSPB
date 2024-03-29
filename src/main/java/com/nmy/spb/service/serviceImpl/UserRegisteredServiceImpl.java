package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.UserRegisteredDto;
import com.nmy.spb.mapper.AccountSecurityMapper;
import com.nmy.spb.mapper.InitTableMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.service.UserRegisteredService;
import com.nmy.spb.utils.DataVerificationTool;
import com.nmy.spb.utils.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author nmy
 * @title: UserRegisteredServiceImpl
 * @date 2022-01-23 21:05
 */
@Service
public class UserRegisteredServiceImpl implements UserRegisteredService {

    private static final String[] PATH = {"/HeadImage/", "/BackgroundImage/", "/PostBarImage/"
            , "/APostBarImage/", "/Voice/", "/Video/", "/Diary/", "/Other/"};

    @Resource
    AccountSecurityMapper accountSecurityMapper;

    @Resource
    InitTableMapper initTableMapper;

    @Resource
    SqlResultService sqlResultService;
    @Autowired
    PasswordEncoder pas;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String userRegistered(UserRegisteredDto userRegisteredDto, MultipartFile file) {
        String account = userRegisteredDto.getUser_account();
        String password = userRegisteredDto.getUser_password();
        String userName = userRegisteredDto.getUser_name();
        if (DataVerificationTool.isEmpty(account, password, userName)) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        if (accountSecurityMapper.queryVerifyStu(account) == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_STU_ISNOT);
        }
        if (accountSecurityMapper.queryVerifyUser(account) != SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_REGISTERED);
        }
        if (accountSecurityMapper.queryVerifyUserName(userRegisteredDto.getUser_name()) != SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_NAME_REPEAT);
        }
        try {
            int ai = initTableMapper.addSignRegistered(account);
            int bi = initTableMapper.addUserRegistered(account, userName,
                    FileUpload.PREFIX_LOCAL + account + FileUpload.HEAD_IMAGE_PATH + FileUpload.HEAD_IMAGE_NAME);
            int ci = initTableMapper.addUsersRegistered(account, pas.encode(password));
            if (sqlResultService.transactionalProcess(ai, bi, ci)) {
                if (!createFolder(account)) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
                }
                if (FileUpload.getOneImageUrl(file, account, FileUpload.HEAD_IMAGE_PATH) == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return sqlResultService.noProcess(EnumCode.ERROR_HEADIMAGE_UPLOAD);
                }
                return sqlResultService.noProcess(EnumCode.SUCCESS_REGISTER);
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }

    private boolean createFolder(String account) {
        boolean key = true;
        for (String s : PATH) {
            File path = new File(FileUpload.PREFIX + account + s);
            if (!path.exists()) {
                key = path.mkdirs();
            }
            if (!key) {
                return false;
            }
        }
        return true;
    }
}
