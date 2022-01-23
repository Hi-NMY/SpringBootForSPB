package com.nmy.spb.controller;

import com.nmy.spb.domain.dto.UserRegisteredDto;
import com.nmy.spb.service.UserRegisteredService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: UserRegisteredController
 * @date 2022-01-23 19:56
 */
@Controller
@ResponseBody
@RequestMapping("/registered")
public class UserRegisteredController {

    @Resource
    UserRegisteredService userRegisteredService;

    @RequestMapping("/userRegistered")
    public String userRegistered(UserRegisteredDto userRegisteredDto, @RequestParam("file") MultipartFile file){
        return userRegisteredService.userRegistered(userRegisteredDto, file);
    }

}
