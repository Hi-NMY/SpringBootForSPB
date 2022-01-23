package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.service.DateService;
import com.nmy.spb.utils.DateTool;
import org.springframework.stereotype.Service;

/**
 * @author nmy
 * @title: DateServiceImpl
 * @date 2022-01-23 12:16
 */
@Service
public class DateServiceImpl implements DateService {

    @Override
    public String dateTime() {
        return DateTool.obtainNowDateTime();
    }

}
