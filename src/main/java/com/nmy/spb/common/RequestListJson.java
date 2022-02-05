package com.nmy.spb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author nmy
 * @title: RequestEntityJson
 * @date 2022-01-25 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestListJson<T> {

    private RequestCode resultCode;

    private List<T> dataList;

    public RequestListJson(EnumCode e, List<T> data) {
        this.dataList = data;
        resultCode = new RequestCode(e.getCode(), e.getMessage());
    }
}
