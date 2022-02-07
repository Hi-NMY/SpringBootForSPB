package com.nmy.spb.service;

import com.nmy.spb.domain.dto.UserInformationDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nmy
 * @title: UserService
 * @date 2022-01-29 17:53
 */
public interface UserService {

    String querySchoolTable();

    String querySearchUser(String search);

    String updateUserPersonalInformation(UserInformationDto info);

    String updateUserIp(String userAccount, String ip);

    String updateUserToken(String userAccount, String token);

    String updateUserHeadImage(MultipartFile file, String userAccount);

    String updateUserBgImage(MultipartFile file, String userAccount);

    String updateUserBadgeImage(String userBadge, String userAccount);

    String updateUserPrivacy(String userPrivacy, String userAccount);

    String deleteUserIp(String userAccount);


}
