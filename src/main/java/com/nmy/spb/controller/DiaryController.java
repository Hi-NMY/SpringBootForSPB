package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.pojo.Diary;
import com.nmy.spb.service.DiaryService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "日记控制")
public class DiaryController {

    @Resource
    DiaryService diaryService;

    @RequestMapping(path = "/queryDiary", method = RequestMethod.POST)
    @ApiOperation(value = "获取日记列表", notes = "RequestListJson -> 状态码&Diary")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryDiary(@RequestParam("user_account") String userAccount) {
        return diaryService.queryDiary(userAccount);
    }

    @RequestMapping(path = "/addDiary", method = RequestMethod.POST)
    @ApiOperation(value = "添加日记", notes = "Boot使用Diary解析:\n" +
            "public String addDiary(Diary diary, @RequestParam(\"file\") MultipartFile file)\n" +
            "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已发布", response = RequestCode.class)
    })
    public String addDiary(Diary diary, @RequestParam("file") MultipartFile file) {
        return diaryService.addDiary(diary, file);
    }

    @RequestMapping(path = "/deleteDiary", method = RequestMethod.POST)
    @ApiOperation(value = "删除日记", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "日记id", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已删除", response = RequestCode.class)
    })
    public String deleteDiary(@RequestParam("user_account") String userAccount, @RequestParam("id") String id) {
        return diaryService.deleteDiary(userAccount, id);
    }

}
