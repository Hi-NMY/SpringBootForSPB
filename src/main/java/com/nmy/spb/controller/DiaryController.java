package com.nmy.spb.controller;

import com.nmy.spb.domain.pojo.Diary;
import com.nmy.spb.service.DiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: DiaryController
 * @date 2022-01-27 22:29
 */
@Controller
@ResponseBody
@RequestMapping("/diary")
public class DiaryController {

    @Resource
    DiaryService diaryService;

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestListJson -> 状态码&Diary
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 23:31
     */
    @RequestMapping("/queryDiary")
    public String queryDiary(@RequestParam("user_account") String userAccount) {
        return diaryService.queryDiary(userAccount);
    }

    /**
     * @Description: 必要数据：Diary -> user_account,dia_date,dia_weather,dia_message    file
     * 返回：RequestJson -> 状态码
     * @Param: [diary, file]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 23:32
     */
    @RequestMapping("/addDiary")
    public String addDiary(Diary diary, @RequestParam("file") MultipartFile file) {
        return diaryService.addDiary(diary, file);
    }

    /**
     * @Description: 必要数据：user_account,id
     * 返回：RequestJson -> 状态码
     * @Param: [userAccount, id]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-27 23:33
     */
    @RequestMapping("/deleteDiary")
    public String deleteDiary(@RequestParam("user_account") String userAccount, @RequestParam("id") String id) {
        return diaryService.deleteDiary(userAccount, id);
    }

}
