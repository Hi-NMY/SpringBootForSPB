package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.SearchUserDto;
import com.nmy.spb.domain.dto.UserInformationDto;
import com.nmy.spb.domain.pojo.SchoolTable;
import com.nmy.spb.mapper.UserMapper;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.service.UserService;
import com.nmy.spb.utils.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: UserServiceImpl
 * @date 2022-01-29 17:55
 */
@Controller
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String querySchoolTable() {
        List<SchoolTable> schoolTables = userMapper.querySchoolTable();
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, schoolTables));
    }

    @Override
    public String querySearchUser(String search) {
        List<SearchUserDto> searchUserDtos = userMapper.querySearchUser(search);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT,searchUserDtos));
    }

    @Override
    public String updateUserPersonalInformation(UserInformationDto info) {
        int value = userMapper.updateUserPersonalInformation(info);
        if (value == SQLResultCode.ERROR){
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_USER_INFORMATION);
    }

    @Override
    public String updateUserHeadImage(MultipartFile file, String userAccount) {
        String oneImageUrl = FileUpload.getOneImageUrl(file, userAccount, FileUpload.HEAD_IMAGE_PATH);
        int value = userMapper.updateUserHeadImage(oneImageUrl, userAccount);
        if (value == SQLResultCode.ERROR){
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
    }

    @Override
    public String updateUserBgImage(MultipartFile file, String userAccount) {
        String oneImageUrl = FileUpload.getOneImageUrl(file, userAccount, FileUpload.BG_IMAGE_PATH);
        int value = userMapper.updateUserHeadImage(oneImageUrl, userAccount);
        if (value == SQLResultCode.ERROR){
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
    }

    @Override
    public String updateUserBadgeImage(String userBadge, String userAccount) {
        int value = userMapper.updateUserBadgeImage(userBadge, userAccount);
        if (value == SQLResultCode.ERROR){
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
    }

    @Override
    public String updateUserPrivacy(String userPrivacy, String userAccount) {
        int value = userMapper.updateUserPrivacy(userPrivacy, userAccount);
        if (value == SQLResultCode.ERROR){
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_USER_INFORMATION);
    }

    @Override
    public String deleteUserIp(String userAccount) {
        int value = userMapper.deleteUserIp(userAccount);
        if (value == SQLResultCode.ERROR){
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DEFAULT);
    }
}
