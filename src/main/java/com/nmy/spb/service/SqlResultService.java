package com.nmy.spb.service;

import com.nmy.spb.common.EnumCode;

/**
 * @author nmy
 * @title: SqlResultService
 * @date 2022-01-22 19:54
 */
public interface SqlResultService {

    String noProcess(EnumCode code);

    String process(Object Obj);

    boolean transactionalProcess(int... keys);

}
