package com.nmy.spb.service;

/**
 * @author nmy
 * @title: FollowService
 * @date 2022-01-23 10:08
 */
public interface FollowService {

    String queryFollowList(String userAccount);

    String queryFollowUserList(String userAccount);

    String addFollow(String followAccount, String followedAccount);

    String deleteFollow(String followAccount, String followedAccount);

}
