package com.nmy.spb.service;

/**
 * @author nmy
 * @title: FollowedService
 * @date 2022-01-23 10:09
 */
public interface FollowedService {

    String queryFollowedList(String userAccount);

    String queryFollowedUserList(String userAccount);

}
