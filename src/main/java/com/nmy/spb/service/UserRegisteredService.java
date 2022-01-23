package com.nmy.spb.service;

import com.nmy.spb.domain.dto.UserRegisteredDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nmy
 * @title: UserRegisteredService
 * @date 2022-01-23 21:04
 */
public interface UserRegisteredService {

    String userRegistered(UserRegisteredDto userRegisteredDto, MultipartFile file);

}
