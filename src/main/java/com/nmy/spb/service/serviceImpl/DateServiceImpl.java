package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.service.DateService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DateTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: DateServiceImpl
 * @date 2022-01-23 12:16
 */
@Service
public class DateServiceImpl implements DateService {

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String dateTime() {
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, DateTool.obtainNowDateTime()));
    }

}
