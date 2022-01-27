package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.pojo.Diary;
import com.nmy.spb.mapper.DiaryMapper;
import com.nmy.spb.service.DiaryService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: DiaryServiceImpl
 * @date 2022-01-27 22:33
 */
@Service
public class DiaryServiceImpl implements DiaryService {

    @Resource
    DiaryMapper diaryMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryDiary(String userAccount) {
        List<Diary> diaryList = diaryMapper.queryDiary(userAccount);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, diaryList));
    }

    @Override
    public String addDiary(Diary diary, MultipartFile file) {
        String path = FileUpload.getOneImageUrl(file, diary.getUser_account(), FileUpload.DIARY_IMAGE_PATH);
        if (path == null) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        diary.setDia_image(path);
        int value = diaryMapper.addDiary(diary);
        if (value == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DIARY);
    }

    @Override
    public String deleteDiary(String userAccount, String id) {
        int value = diaryMapper.deleteDiary(userAccount, Integer.parseInt(id));
        if (value == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_DIARY);
    }
}
