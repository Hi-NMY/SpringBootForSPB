package com.nmy.spb.service;

import com.nmy.spb.domain.pojo.Diary;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nmy
 * @title: DiaryService
 * @date 2022-01-27 22:32
 */
public interface DiaryService {

    String queryDiary(String userAccount);

    String addDiary(Diary diary, MultipartFile file);

    String deleteDiary(String userAccount, String id);

}
