package com.nmy.spb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author nmy
 * @title: FollowService
 * @date 2022-01-23 10:08
 */
public interface FollowService {

    String queryFollowList(String userAccount);

    String queryFollowUserList(String userAccount);

    String addFollow(String cacheAccount,String userAccount);

    String deleteFollow(String cacheAccount,String userAccount);

}
