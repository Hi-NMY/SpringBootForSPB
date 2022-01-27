package com.nmy.spb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: RequestJson
 * @date 2022-01-25 23:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestJson {

    private RequestCode resultCode;

    public RequestJson(EnumCode e) {
        resultCode = new RequestCode(e.getCode(), e.getMessage());
    }

    @Data
    @AllArgsConstructor
    static class RequestCode {
        private int code;
        private String messgae;
    }

}
