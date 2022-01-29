package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.BarDto;
import com.nmy.spb.domain.pojo.Bar;
import com.nmy.spb.mapper.PostBarMapper;
import com.nmy.spb.mapper.TopicMapper;
import com.nmy.spb.service.PostBarService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DataVerificationTool;
import com.nmy.spb.utils.DateTool;
import com.nmy.spb.utils.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author nmy
 * @title: PostBarServiceImpl
 * @date 2022-01-28 14:05
 */
@Service
public class PostBarServiceImpl implements PostBarService {

    private static final int DEFAULT_THUMB = 10000000;

    @Resource
    PostBarMapper postBarMapper;

    @Resource
    TopicMapper topicMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryNoVideoBarListForDate(String pbDate) {
        String date = forDate(pbDate);
        List<BarDto> barDtos = postBarMapper.queryNoVideoBarListForDate(date);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String queryNoVideoTopicBarListForThumbNum(String thumbNum, String topicName) {
        int thumb = 0;
        if (DataVerificationTool.isEmpty(thumbNum)) {
            thumb = DEFAULT_THUMB;
        } else {
            thumb = Integer.parseInt(thumbNum);
        }
        List<BarDto> barDtos = postBarMapper.queryNoVideoTopicBarListForThumbNum(thumb, topicName);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String queryNoVideoTopicBarListForDate(String pbDate, String topicName) {
        String date = forDate(pbDate);
        List<BarDto> barDtos = postBarMapper.queryNoVideoTopicBarListForDate(date, topicName);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String queryNoVideoUserBarListForDate(String userAccount, String pbDate) {
        String date = forDate(pbDate);
        List<BarDto> barDtos = postBarMapper.queryNoVideoUserBarListForDate(userAccount, date);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String queryUserBarCount(String userAccount) {
        int value = postBarMapper.queryUserBarCount(userAccount);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, String.valueOf(value)));
    }

    @Override
    public String queryNoVideoFollowBarListForDate(String userAccount, String pbDate) {
        String date = forDate(pbDate);
        List<BarDto> barDtos = postBarMapper.queryNoVideoFollowBarListForDate(userAccount, date);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String queryBarDetatilForPbid(String pbid) {
        BarDto barDto = postBarMapper.queryBarDetatilForPbid(pbid);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, barDto));
    }

    @Override
    public String queryUserBarLikeCount(String userAccount) {
        int value = postBarMapper.queryUserBarLikeCount(userAccount);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, String.valueOf(value)));
    }

    @Override
    public String queryNoVideoSearchBarListForDate(String searchArt) {
        List<BarDto> barDtos = postBarMapper.queryNoVideoSearchBarListForDate(searchArt);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String queryVideoBarListForDate(String pbDate) {
        String date = forDate(pbDate);
        List<BarDto> barDtos = postBarMapper.queryVideoBarListForDate(date);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String queryVideoTopicBarListForDate(String pbDate, String topicName) {
        String date = forDate(pbDate);
        List<BarDto> barDtos = postBarMapper.queryVideoTopicBarListForDate(date, topicName);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String queryVideoUserBarListForDate(String userAccount, String pbDate) {
        String date = forDate(pbDate);
        List<BarDto> barDtos = postBarMapper.queryVideoUserBarListForDate(userAccount, date);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, barDtos));
    }

    @Override
    public String deleteBar(String pbid) {
        int value = postBarMapper.deleteBar(pbid);
        if (value == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_BAR);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addBar(Bar bar, List<MultipartFile> image, MultipartFile voice, MultipartFile video) {
        if (image != null && image.size() > 0) {
            String postBarImageUrl = FileUpload.getPostBarImageUrl(image, bar.getUser_account());
            if (postBarImageUrl == null) {
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
            bar.setPb_image_url(postBarImageUrl);
        }

        if (voice != null) {
            String postBarVoiceUrl = FileUpload.getPostBarVoiceUrl(voice, bar.getUser_account());
            if (postBarVoiceUrl == null) {
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
            bar.setPb_voice(postBarVoiceUrl);
        }

        if (video != null){
            String postBarVideoUrl = FileUpload.getPostBarVideoUrl(video, bar.getUser_account());
            if (postBarVideoUrl == null) {
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
            bar.setPb_video(postBarVideoUrl);
        }

        bar.setPb_date(DateTool.obtainNowDateTime());
        bar.setPb_one_id(FileUpload.toStringMD5(DateTool.obtainNowDateTime() + bar.getUser_account()) + bar.getUser_account());

        try {
            int value = postBarMapper.addBar(bar);
            int ai = SQLResultCode.SUCCEES;
            if (!DataVerificationTool.isEmpty(bar.getPb_topic())) {
                List<String> strings = Arrays.asList(bar.getPb_topic().split("\\|"));
                ai = topicMapper.updateIncreaseBarNum(strings);
            }
            if (sqlResultService.transactionalProcess(value, ai)) {
                return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_BAR, bar));
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }

    private String forDate(String aDate) {
        if (DataVerificationTool.isEmpty(aDate)) {
            return DateTool.obtainNowDateTime();
        } else {
            return aDate;
        }
    }
}
