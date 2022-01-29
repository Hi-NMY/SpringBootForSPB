package com.nmy.spb.service;

import com.nmy.spb.domain.dto.BarDto;
import com.nmy.spb.domain.pojo.Bar;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author nmy
 * @title: PostBarService
 * @date 2022-01-28 14:02
 */
public interface PostBarService {

    String queryNoVideoBarListForDate(String pbDate);

    String queryNoVideoTopicBarListForThumbNum(String thumbNum, String topicName);

    String queryNoVideoTopicBarListForDate(String pbDate, String topicName);

    String queryNoVideoUserBarListForDate(String userAccount, String pbDate);

    String queryUserBarCount(String userAccount);

    String queryNoVideoFollowBarListForDate(String userAccount, String pbDate);

    String queryBarDetatilForPbid(String pbid);

    String queryUserBarLikeCount(String userAccount);

    String queryNoVideoSearchBarListForDate(String searchArt);

    String queryVideoBarListForDate(String pbDate);

    String queryVideoTopicBarListForDate(String pbDate, String topicName);

    String queryVideoUserBarListForDate(String userAccount, String pbDate);

    String deleteBar(String pbid);

    String addBar(Bar bar, List<MultipartFile> image, MultipartFile voice, MultipartFile video);

}
