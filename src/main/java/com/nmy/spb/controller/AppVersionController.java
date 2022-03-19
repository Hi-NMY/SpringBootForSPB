package com.nmy.spb.controller;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.domain.dto.AppVersionDto;
import com.nmy.spb.mapper.AppVersionMapper;
import com.nmy.spb.service.SqlResultService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "app版本控制")
public class AppVersionController {

    @Resource
    AppVersionMapper appVersionMapper;

    @Resource
    SqlResultService sqlResultService;

    @RequestMapping(path = "/isVerison", method = RequestMethod.POST)
    @ApiOperation(value = "获取版本更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionCode", value = "版本号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "已是最新版本", response = RequestEntityJson.class),
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String isVerison(@RequestParam("versionCode") String versionCode) {
        AppVersionDto version = appVersionMapper.isVersion(versionCode);
        if (version.getVersionCode() == Integer.parseInt(versionCode)) {
            return sqlResultService.process(new RequestEntityJson<>(EnumCode.ERROR_VERSION, null));
        }
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, version));
    }
}
