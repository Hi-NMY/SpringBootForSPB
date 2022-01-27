package com.nmy.spb.utils;

/**
 * @author nmy
 * @title: DataVerificationTool
 * @date 2022-01-25 23:42
 */
public class DataVerificationTool {

    public static boolean isEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || "".equals(str) || str.length() == 0) {
                return true;
            }
            if ("null".equals(str)) {
                return true;
            }
        }
        return false;
    }

}
