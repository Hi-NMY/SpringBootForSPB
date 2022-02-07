package com.nmy.spb.controller;

import com.nmy.spb.domain.pojo.Bar;
import com.nmy.spb.service.PostBarService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class PostBarController {

    @Resource
    PostBarService postBarService;

    /**
     * @Description: 必要数据：pb_date
     * 返回：RequestListJson -> 状态码&BarDto
     * 特殊：pb_date可置空
     * @Param: [pbDate]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:37
     */
    @RequestMapping("/queryNoVideoBarListForDate")
    public String queryNoVideoBarListForDate(@RequestParam("pb_date") String pbDate) {
        return postBarService.queryNoVideoBarListForDate(pbDate);
    }

    /**
     * @Description: 必要数据：pb_thumb_num,topic_name
     * 返回：RequestListJson -> 状态码&BarDto
     * 特殊：pb_thumb_num可置空
     * @Param: [thumbNum, topicName]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:38
     */
    @RequestMapping("/queryNoVideoTopicBarListForThumbNum")
    public String queryNoVideoTopicBarListForThumbNum(@RequestParam("pb_thumb_num") String thumbNum, @RequestParam("topic_name") String topicName) {
        return postBarService.queryNoVideoTopicBarListForThumbNum(thumbNum, topicName);
    }

    /**
     * @Description: 必要数据：pb_date,topic_name
     * 返回：RequestListJson -> 状态码&BarDto
     * 特殊：pb_date可置空
     * @Param: [pbDate, topicName]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:39
     */
    @RequestMapping("/queryNoVideoTopicBarListForDate")
    public String queryNoVideoTopicBarListForDate(@RequestParam("pb_date") String pbDate, @RequestParam("topic_name") String topicName) {
        return postBarService.queryNoVideoTopicBarListForDate(pbDate, topicName);
    }

    /**
     * @Description: 必要数据：user_account,pb_date
     * 返回：RequestListJson -> 状态码&BarDto
     * 特殊：pb_date可置空
     * @Param: [userAccount, pbDate]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:40
     */
    @RequestMapping("/queryNoVideoUserBarListForDate")
    public String queryNoVideoUserBarListForDate(@RequestParam("user_account") String userAccount, @RequestParam("pb_date") String pbDate) {
        return postBarService.queryNoVideoUserBarListForDate(userAccount, pbDate);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestEntityJson -> 状态码&String(count)
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:41
     */
    @RequestMapping("/queryUserBarCount")
    public String queryUserBarCount(@RequestParam("user_account") String userAccount) {
        return postBarService.queryUserBarCount(userAccount);
    }

    /**
     * @Description: 必要数据：user_account,pb_date
     * 返回：RequestListJson -> 状态码&BarDto
     * 特殊：pb_date可置空
     * @Param: [userAccount, pbDate]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:41
     */
    @RequestMapping("/queryNoVideoFollowBarListForDate")
    public String queryNoVideoFollowBarListForDate(@RequestParam("user_account") String userAccount, @RequestParam("pb_date") String pbDate) {
        return postBarService.queryNoVideoFollowBarListForDate(userAccount, pbDate);
    }

    /**
     * @Description: 必要数据：pb_one_id
     * 返回：RequestEntityJson -> 状态码&BarDto
     * @Param: [pbid]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:42
     */
    @RequestMapping("/queryBarDetatilForPbid")
    public String queryBarDetatilForPbid(@RequestParam("pb_one_id") String pbid) {
        return postBarService.queryBarDetatilForPbid(pbid);
    }

    /**
     * @Description: 必要数据：user_account
     * 返回：RequestEntityJson -> 状态码&String(count)
     * @Param: [userAccount]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:42
     */
    @RequestMapping("/queryUserBarLikeCount")
    public String queryUserBarLikeCount(@RequestParam("user_account") String userAccount) {
        return postBarService.queryUserBarLikeCount(userAccount);
    }

    /**
     * @Description: 必要数据：search_art
     * 返回：RequestListJson -> 状态码&BarDto
     * @Param: [searchArt]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:44
     */
    @RequestMapping("/queryNoVideoSearchBarListForDate")
    public String queryNoVideoSearchBarListForDate(@RequestParam("search_art") String searchArt) {
        return postBarService.queryNoVideoSearchBarListForDate(searchArt);
    }

    /**
     * @Description: 必要数据：pb_date
     * 返回：RequestListJson -> 状态码&BarDto
     * 特殊：pb_date可置空
     * @Param: [pbDate]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:44
     */
    @RequestMapping("/queryVideoBarListForDate")
    public String queryVideoBarListForDate(@RequestParam("pb_date") String pbDate) {
        return postBarService.queryVideoBarListForDate(pbDate);
    }

    /**
     * @Description: 必要数据：pb_date,topic_name
     * 返回：RequestListJson -> 状态码&BarDto
     * 特殊：pb_date可置空
     * @Param: [pbDate, topicName]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:45
     */
    @RequestMapping("/queryVideoTopicBarListForDate")
    public String queryVideoTopicBarListForDate(@RequestParam("pb_date") String pbDate, @RequestParam("topic_name") String topicName) {
        return postBarService.queryVideoTopicBarListForDate(pbDate, topicName);
    }

    /**
     * @Description: 必要数据：pb_date,topic_name
     * 返回：RequestListJson -> 状态码&BarDto
     * 特殊：pb_date可置空
     * @Param: [userAccount, pbDate]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:45
     */
    @RequestMapping("/queryVideoUserBarListForDate")
    public String queryVideoUserBarListForDate(@RequestParam("user_account") String userAccount, @RequestParam("pb_date") String pbDate) {
        return postBarService.queryVideoUserBarListForDate(userAccount, pbDate);
    }

    /**
     * @Description: 必要数据：pb_one_id
     * 返回：RequestCode -> 状态码
     * @Param: [pbid]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-28 17:46
     */
    @RequestMapping("/deleteBar")
    public String deleteBar(@RequestParam("pb_one_id") String pbid) {
        return postBarService.deleteBar(pbid);
    }

    /**
     * @Description: 必要数据：Bar-> user_account,pb_article,pb_topic,pb_location
     * 非必要数据：image_file,voice_file,video_file
     * 返回：RequestEntityJson -> 状态码&BarDto
     * 特殊：pb_topic,pb_location无数据时可置空
     * @Param: [bar, image, voice]
     * @return: java.lang.String
     * @Author: nmy
     * @Date: 2022-01-29 16:55
     */
    @RequestMapping("/addBar")
    public String addBar(Bar bar, @Nullable @RequestParam("image_file") List<MultipartFile> image
            , @Nullable @RequestParam("voice_file") MultipartFile voice) {
        return postBarService.addBar(bar, image, voice);
    }

    @RequestMapping("/addBarVideo")
    public String addBarVideo(Bar bar, @Nullable @RequestParam("video_file") MultipartFile video
            , @Nullable @RequestParam("video_image_file") MultipartFile videoImg) {
        return postBarService.addBarVideo(bar, video, videoImg);
    }
}
