package com.nmy.spb.controller;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.domain.dto.AppVersionDto;
import com.nmy.spb.mapper.AppVersionMapper;
import com.nmy.spb.service.SqlResultService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: AppVersion
 * @date 2022-02-07 17:04
 */
@Controller
@ResponseBody
@RequestMapping("/appVersion")
public class AppVersionController {

    @Resource
    AppVersionMapper appVersionMapper;

    @Resource
    SqlResultService sqlResultService;

    @RequestMapping("/isVerison")
    public String isVerison(@RequestParam("versionCode") String versionCode) {
        AppVersionDto version = appVersionMapper.isVersion(versionCode);
        if (version.getVersionCode() == Integer.parseInt(versionCode)) {
            return sqlResultService.noProcess(EnumCode.ERROR_VERSION);
        }
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, version));
    }
}
