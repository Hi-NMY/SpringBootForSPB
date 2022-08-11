package com.nmy.spb.controller;

import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.service.DateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: DateController
 * @date 2022-01-23 12:15
 */
@Controller
@ResponseBody
@RequestMapping("/date")
@Api(tags = "服务时间控制")
public class DateController {

    @Resource
    DateService dateService;

    @RequestMapping(path = "/dateTime", method = RequestMethod.GET)
    @ApiOperation(value = "获取服务时间", notes = "RequestEntityJson -> 状态码&String(时间)\n" +
            "GMT+8  日期格式 -> yyyy-MM-dd HH:mm:ss")
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String dateTime() {
        return dateService.dateTime();
    }

}
