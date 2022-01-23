package com.nmy.spb.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmy.spb.common.RequestResultCode;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.service.SqlResultService;
import org.springframework.stereotype.Service;

/**
 * @author nmy
 * @title: SqlResultServiceImpl
 * @date 2022-01-22 20:07
 */
@Service
public class SqlResultServiceImpl implements SqlResultService {

    private ObjectMapper objectMapper;

    @Override
    public String noProcess(int code) {
        if (code == SQLResultCode.SUCCEES) {
            return RequestResultCode.SUCCEES;
        } else {
            return RequestResultCode.ERROR;
        }
    }

    @Override
    public String process(Object Obj) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            String jsonString = objectMapper.writeValueAsString(Obj);
            if ("null".equals(jsonString)) {
                jsonString = "[]";
            }
            return RequestResultCode.SUCCEES + jsonString;
        } catch (JsonProcessingException e) {
            return RequestResultCode.ERROR;
        }
    }

    @Override
    public boolean transactionalProcess(int... keys) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == SQLResultCode.ERROR) {
                return false;
            }
        }
        return true;
    }
}
