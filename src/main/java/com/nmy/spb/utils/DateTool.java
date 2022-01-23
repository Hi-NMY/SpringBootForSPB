package com.nmy.spb.utils;

import java.time.LocalDateTime;

/**
 * @author nmy
 * @title: DateTool
 * @date 2022-01-22 18:33
 */
public class DateTool {

    public static String obtainNowDateTime(){
        return LocalDateTime.now().toString();
    }

}
