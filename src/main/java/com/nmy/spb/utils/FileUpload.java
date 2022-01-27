package com.nmy.spb.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author nmy
 * @title: FileUpload
 * @date 2022-01-27 22:47
 */
public class FileUpload {

    public static final String PREFIX = "D:/php/php/PHPTutorial/WWW/spb/UserImageServer/";

    public static final String PREFIX_LOCAL = "upload/UserImageServer/";

    public static final String HEAD_IMAGE_PATH = "/HeadImage/";

    public static final String HEAD_IMAGE_NAME = "myHeadImage.png";

    public static final String BG_IMAGE_PATH = "/BackgroundImage/";

    public static final String BG_IMAGE_NAME = "myBackgroundImage.png";

    public static final String DIARY_IMAGE_PATH = "/Diary/";

    public static String getOneImageUrl(MultipartFile f, String account, String path) {
        try {
            String originalFilename = f.getOriginalFilename();
            String contentType = f.getContentType();
            if ("".equals(originalFilename)) {
                return null;
            }

            if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
                return null;
            }

            if (HEAD_IMAGE_PATH.equals(path)) {
                f.transferTo(new File(PREFIX + account + HEAD_IMAGE_PATH + HEAD_IMAGE_NAME));
                return PREFIX_LOCAL + account + HEAD_IMAGE_PATH + HEAD_IMAGE_NAME;
            }

            if (BG_IMAGE_PATH.equals(path)) {
                f.transferTo(new File(PREFIX + account + BG_IMAGE_PATH + BG_IMAGE_NAME));
                return PREFIX_LOCAL + account + BG_IMAGE_PATH + BG_IMAGE_NAME;
            }

            String fileName = toStringMD5(DateTool.obtainNowDateTime()) + ".png";
            f.transferTo(new File(PREFIX + account + path + fileName));
            return PREFIX_LOCAL + account + path + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toStringMD5(String code) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        BASE64Encoder b = new BASE64Encoder();
        return b.encode(m.digest(code.getBytes(StandardCharsets.UTF_8)));
    }

}
