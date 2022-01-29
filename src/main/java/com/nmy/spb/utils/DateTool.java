package com.nmy.spb.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author nmy
 * @title: DateTool
 * @date 2022-01-22 18:33
 */
public class DateTool {

    public static String obtainNowDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(dateTime);
    }

}
