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

    @RequestMapping("/dateTime")
    public String dateTime(){
        return dateService.dateTime();
    }

}
