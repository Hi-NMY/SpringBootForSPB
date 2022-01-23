package com.nmy.spb.service;

import java.util.List;

/**
 * @author nmy
 * @title: SqlResultService
 * @date 2022-01-22 19:54
 */
public interface SqlResultService{

    String noProcess(int code);

    String process(Object Obj);

    boolean transactionalProcess(int... keys);

}
