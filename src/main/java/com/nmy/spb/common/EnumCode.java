package com.nmy.spb.common;

/**
 * @author nmy
 * @title: EnumCode
 * @date 2022-01-25 15:21
 */
public enum EnumCode {

    SUCCESS_DEFAULT(200, ""),
    ERROR_DEFAULT(4004, "错误，请重试"),

    SUCCESS_ADD_ATTENTONTOPIC(200, "已收藏话题"),
    SUCCESS_DELETE_ATTENTONTOPIC(200, "取消收藏"),


    SUCCESS_COMMENT(200, "已评论"),
    SUCCESS_DELETE_COMMENT(200, "删除评论"),


    SUCCESS_FOLLOW(200, "已关注"),
    SUCCESS_DELETE_FOLLOW(200, "取消关注"),


    SUCCESS_REGISTER(200, "注册成功"),
    ERROR_STU_ISNOT(4004, "您非本校学生"),
    ERROR_REGISTERED(4004, "该账号已注册"),
    ERROR_NAME_REPEAT(4004, "用户名重复"),
    ERROR_HEADIMAGE_UPLOAD(4004, "头像上传失败"),


    SUCCESS_UPDATA_PASSWORD(200, "密码修改成功"),
    ;


    private int code;
    private String message;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    EnumCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}