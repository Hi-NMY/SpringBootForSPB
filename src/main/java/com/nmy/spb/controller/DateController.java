package com.nmy.spb.controller;

import com.nmy.spb.service.DateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class DateController {

    @Resource
    DateService dateService;

    /**
     * @Description: 必要数据：无
     * 返回：RequestEntityJson -> 状态码&String(时间)
     * 特殊：GMT+8  日期格式 -> yyyy-MM-dd HH:mm:ss
     * @Param: []
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 11:19
     */
    @RequestMapping("/dateTime")
    public String dateTime(){
        return dateService.dateTime();
    }

}
