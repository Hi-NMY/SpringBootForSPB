package com.nmy.spb.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author nmy
 * @title: FileUpload
 * @date 2022-01-27 22:47
 */
public class FileUpload {

    private static final String PNG_SUFFIX = ".png";

    private static final String JPEG_SUFFIX = ".jpeg";

    private static final String JPG_SUFFIX = ".jpg";

    public static final String PREFIX = "D:/php/php/PHPTutorial/WWW/spb/UserImageServer/";

    public static final String PREFIX_LOCAL = "upload/UserImageServer/";

    public static final String HEAD_IMAGE_PATH = "/HeadImage/";

    public static final String HEAD_IMAGE_NAME = "myHeadImage.png";

    public static final String BG_IMAGE_PATH = "/BackgroundImage/";

    public static final String BG_IMAGE_NAME = "myBackgroundImage.png";

    public static final String DIARY_IMAGE_PATH = "/Diary/";

    public static final String POSTBAR_A_IMAGE_PATH = "/APostBarImage/";

    public static final String POSTBAR_B_IMAGE_PATH = "/PostBarImage/";

    public static final String POSTBAR_VIOCE_PATH = "/Voice/";

    public static final String POSTBAR_VIDEO_PATH = "/Video/";


    public static String getOneImageUrl(MultipartFile f, String account, String path) {
        try {
            if (f == null){
                return null;
            }
            String originalFilename = f.getOriginalFilename();
            String contentType = f.getContentType();
            if ("".equals(originalFilename)) {
                return null;
            }

            if (isNotImageType(contentType)){
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

            String fileName = toStringMD5(DateTool.obtainNowDateTime()) + PNG_SUFFIX;
            f.transferTo(new File(PREFIX + account + path + fileName));
            return PREFIX_LOCAL + account + path + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPostBarImageUrl(List<MultipartFile> f, String account) {
        try {
            float a = 0.5f;
            String fileName;
            StringBuffer filePathA = new StringBuffer();
            StringBuffer filePath = new StringBuffer();
            for (int i = 0; i < f.size(); i++) {
                MultipartFile file = f.get(i);
                if (isNotImageType(file.getContentType())){
                    return null;
                }
                String oldName = file.getOriginalFilename();
                String type = null;
                if (oldName != null) {
                    type = oldName.substring(oldName.lastIndexOf("."));
                }
                String newName = toStringMD5(DateTool.obtainNowDateTime());
                if (PNG_SUFFIX.equals(type)){
                    fileName = newName + PNG_SUFFIX;
                } else if (JPEG_SUFFIX.equals(type)) {
                    fileName = newName + JPEG_SUFFIX;
                }else if (JPG_SUFFIX.equals(type)) {
                    fileName = newName + JPG_SUFFIX;
                }else {
                    return null;
                }
                Thumbnails.of(file.getInputStream())
                        .outputFormat("jpg")
                        .scale(1f)
                        .outputQuality(a)
                        .toFile(new File(PREFIX + account + POSTBAR_B_IMAGE_PATH + newName));
                filePath.append(PREFIX_LOCAL).append(account).append(POSTBAR_B_IMAGE_PATH).append(newName).append(JPG_SUFFIX);
                if (i != f.size() - 1){
                    filePath.append("|");
                }
                f.get(i).transferTo(new File(PREFIX + account + POSTBAR_A_IMAGE_PATH + fileName));
                filePathA.append(PREFIX_LOCAL).append(account).append(POSTBAR_A_IMAGE_PATH).append(fileName);
                if (i != f.size() - 1){
                    filePathA.append("|");
                }
            }
            return String.valueOf(filePath.append("@").append(filePathA));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPostBarVoiceUrl(MultipartFile f, String account) {
        try {
            if (isNotVoiceType(f.getContentType())){
                return null;
            }

            String fileName = toStringMD5(DateTool.obtainNowDateTime()) + f.getOriginalFilename();

            f.transferTo(new File(PREFIX + account + POSTBAR_VIOCE_PATH + fileName));
            return PREFIX_LOCAL + account + POSTBAR_VIOCE_PATH + fileName;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPostBarVideoUrl(MultipartFile video,MultipartFile img, String account) {
        try {
            if (isNotVideoType(video.getContentType())){
                return null;
            }
            if (isNotImageType(img.getContentType())){
                return null;
            }

            String fileName = toStringMD5(DateTool.obtainNowDateTime()) + video.getOriginalFilename();
            img.transferTo(new File(PREFIX + account + POSTBAR_VIDEO_PATH + fileName + PNG_SUFFIX));
            video.transferTo(new File(PREFIX + account + POSTBAR_VIDEO_PATH + fileName));
            return PREFIX_LOCAL + account + POSTBAR_VIDEO_PATH + fileName;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isNotImageType(String type){
        return !"image/jpeg".equals(type) && !"image/png".equals(type);
    }

    private static boolean isNotVoiceType(String type){
        return !"audio/m4a".equals(type);
    }

    private static boolean isNotVideoType(String type){
        return !"video/mp4".equals(type);
    }

    public static String toStringMD5(String code){
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            BASE64Encoder b = new BASE64Encoder();
            String a = code + Math.random() * 10000;
            String a1 = b.encode(m.digest(a.getBytes(StandardCharsets.UTF_8)));
            return a1.replaceAll("/", "a");
        }catch (Exception e) {
            String a = code + Math.random() * 10000000;
            return a;
        }
    }
}
