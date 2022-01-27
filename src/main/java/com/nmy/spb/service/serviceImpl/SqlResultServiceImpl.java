package com.nmy.spb.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestJson;
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
    public String noProcess(EnumCode code) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(new RequestJson(code));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Override
    public String process(Object Obj) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(Obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if ("null".equals(jsonString)) {
            jsonString = "[]";
        }

        return jsonString;
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
