package com.nmy.spb.controller;

import com.nmy.spb.common.RequestCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.domain.pojo.Bar;
import com.nmy.spb.service.PostBarService;
import io.swagger.annotations.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: PostBarController
 * @date 2022-01-28 12:37
 */
@Controller
@ResponseBody
@RequestMapping("/postbar")
@Api(tags = "帖子控制")
public class PostBarController {

    @Resource
    PostBarService postBarService;

    @RequestMapping(path = "/queryNoVideoBarListForDate", method = RequestMethod.POST)
    @ApiOperation(value = "根据时间获取帖子列表(无Video)", notes = "RequestListJson -> 状态码&BarDto\n" +
            "pb_date可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_date", value = "第一条帖子时间", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryNoVideoBarListForDate(@RequestParam("pb_date") String pbDate) {
        return postBarService.queryNoVideoBarListForDate(pbDate);
    }

    @RequestMapping(path = "/queryNoVideoTopicBarListForThumbNum", method = RequestMethod.POST)
    @ApiOperation(value = "根据点赞获取话题帖子列表(无Video)", notes = "RequestListJson -> 状态码&BarDto\n" +
            "pb_thumb_num可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_thumb_num", value = "点赞数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "topic_name", value = "话题名", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryNoVideoTopicBarListForThumbNum(@RequestParam("pb_thumb_num") String thumbNum, @RequestParam("topic_name") String topicName) {
        return postBarService.queryNoVideoTopicBarListForThumbNum(thumbNum, topicName);
    }

    @RequestMapping(path = "/queryNoVideoTopicBarListForDate", method = RequestMethod.POST)
    @ApiOperation(value = "根据时间获取话题帖子列表(无Video)", notes = "RequestListJson -> 状态码&BarDto\n" +
            "pb_date可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_date", value = "点赞数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "topic_name", value = "话题名", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryNoVideoTopicBarListForDate(@RequestParam("pb_date") String pbDate, @RequestParam("topic_name") String topicName) {
        return postBarService.queryNoVideoTopicBarListForDate(pbDate, topicName);
    }

    @RequestMapping(path = "/queryNoVideoUserBarListForDate", method = RequestMethod.POST)
    @ApiOperation(value = "根据时间获取用户帖子列表(无Video)", notes = "RequestListJson -> 状态码&BarDto\n" +
            "pb_date可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "空间用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pb_date", value = "时间", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryNoVideoUserBarListForDate(@RequestParam("user_account") String userAccount, @RequestParam("pb_date") String pbDate) {
        return postBarService.queryNoVideoUserBarListForDate(userAccount, pbDate);
    }

    @RequestMapping(path = "/queryUserBarCount", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户帖子数量", notes = "RequestEntityJson -> 状态码&String(count)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryUserBarCount(@RequestParam("user_account") String userAccount) {
        return postBarService.queryUserBarCount(userAccount);
    }

    @RequestMapping(path = "/queryNoVideoFollowBarListForDate", method = RequestMethod.POST)
    @ApiOperation(value = "根据时间获取用户关注帖子列表(无Video)", notes = "RequestListJson -> 状态码&BarDto\n" +
            "pb_date可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pb_date", value = "时间", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryNoVideoFollowBarListForDate(@RequestParam("user_account") String userAccount, @RequestParam("pb_date") String pbDate) {
        return postBarService.queryNoVideoFollowBarListForDate(userAccount, pbDate);
    }

    @RequestMapping(path = "/queryBarDetatilForPbid", method = RequestMethod.POST)
    @ApiOperation(value = "获取帖子详细", notes = "RequestEntityJson -> 状态码&BarDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_one_id", value = "帖子id", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryBarDetatilForPbid(@RequestParam("pb_one_id") String pbid) {
        return postBarService.queryBarDetatilForPbid(pbid);
    }

    @RequestMapping(path = "/queryUserBarLikeCount", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户帖子被赞总数", notes = "RequestEntityJson -> 状态码&String(count)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "用户账号", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestEntityJson.class)
    })
    public String queryUserBarLikeCount(@RequestParam("user_account") String userAccount) {
        return postBarService.queryUserBarLikeCount(userAccount);
    }

    @RequestMapping(path = "/queryNoVideoSearchBarListForDate", method = RequestMethod.POST)
    @ApiOperation(value = "根据时间获取用户搜索帖子列表(无Video)", notes = "RequestListJson -> 状态码&BarDto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search_art", value = "搜索内容", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryNoVideoSearchBarListForDate(@RequestParam("search_art") String searchArt) {
        return postBarService.queryNoVideoSearchBarListForDate(searchArt);
    }

    @RequestMapping(path = "/queryVideoBarListForDate", method = RequestMethod.POST)
    @ApiOperation(value = "根据时间获取帖子列表(有Video)", notes = "RequestListJson -> 状态码&BarDto\n" +
            "pb_date可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search_art", value = "搜索内容", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryVideoBarListForDate(@RequestParam("pb_date") String pbDate) {
        return postBarService.queryVideoBarListForDate(pbDate);
    }

    @RequestMapping(path = "/queryVideoTopicBarListForDate", method = RequestMethod.POST)
    @ApiOperation(value = "根据时间获取话题帖子列表(有Video)", notes = "RequestListJson -> 状态码&BarDto\n" +
            "pb_date可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_date", value = "时间", required = true, paramType = "query"),
            @ApiImplicitParam(name = "topic_name", value = "话题名", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryVideoTopicBarListForDate(@RequestParam("pb_date") String pbDate, @RequestParam("topic_name") String topicName) {
        return postBarService.queryVideoTopicBarListForDate(pbDate, topicName);
    }

    @RequestMapping(path = "/queryVideoUserBarListForDate", method = RequestMethod.POST)
    @ApiOperation(value = "根据时间获取用户帖子列表(有Video)", notes = "RequestListJson -> 状态码&BarDto\n" +
            "pb_date可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_account", value = "空间用户账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pb_date", value = "时间", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "空", response = RequestListJson.class)
    })
    public String queryVideoUserBarListForDate(@RequestParam("user_account") String userAccount, @RequestParam("pb_date") String pbDate) {
        return postBarService.queryVideoUserBarListForDate(userAccount, pbDate);
    }

    @RequestMapping(path = "/deleteBar", method = RequestMethod.POST)
    @ApiOperation(value = "删除帖子", notes = "RequestCode -> 状态码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pb_one_id", value = "帖子id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestCode.class),
            @ApiResponse(code = 200, message = "已删除", response = RequestCode.class)
    })
    public String deleteBar(@RequestParam("pb_one_id") String pbid) {
        return postBarService.deleteBar(pbid);
    }

    @RequestMapping(path = "/addBar", method = RequestMethod.POST)
    @ApiOperation(value = "添加帖子(无Video,有Img或Voice)", notes = "RequestEntityJson -> 状态码&BarDto\n" +
            "pb_topic,pb_location无数据时可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image_file", value = "图片", paramType = "query"),
            @ApiImplicitParam(name = "voice_file", value = "音频", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestEntityJson.class),
            @ApiResponse(code = 200, message = "已发布", response = RequestEntityJson.class)
    })
    public String addBar(Bar bar, @Nullable @RequestParam("image_file") List<MultipartFile> image
            , @Nullable @RequestParam("voice_file") MultipartFile voice) {
        return postBarService.addBar(bar, image, voice);
    }

    @RequestMapping(path = "/addBarVideo", method = RequestMethod.POST)
    @ApiOperation(value = "添加帖子(有Video,无Img和Voice)", notes = "RequestEntityJson -> 状态码&BarDto\n" +
            "pb_topic,pb_location无数据时可置空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "video_file", value = "视频", paramType = "query"),
            @ApiImplicitParam(name = "video_image_file", value = "视频缩略图", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 4004, message = "错误，请重试", response = RequestEntityJson.class),
            @ApiResponse(code = 200, message = "已发布", response = RequestEntityJson.class)
    })
    public String addBarVideo(Bar bar, @Nullable @RequestParam("video_file") MultipartFile video
            , @Nullable @RequestParam("video_image_file") MultipartFile videoImg) {
        return postBarService.addBarVideo(bar, video, videoImg);
    }
}
