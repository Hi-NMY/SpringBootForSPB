package com.nmy.spb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: RequestEntityJson
 * @date 2022-01-25 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntityJson<T> {

    private RequestCode resultCode;

    private T data;

    public RequestEntityJson(EnumCode e, T data) {
        this.data = data;
        resultCode = new RequestCode(e.getCode(), e.getMessage());
    }

    @Data
    @AllArgsConstructor
    static class RequestCode {
        private int code;
        private String messgae;
    }
}
