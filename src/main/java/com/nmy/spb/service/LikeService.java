package com.nmy.spb.service;

/**
 * @author nmy
 * @title: LikeService
 * @date 2022-01-23 11:46
 */
public interface LikeService {

    String queryLike(String userAccount);

    String addLike(String pbId, String userAccount, String cacheAccount);

    String deleteLike(String pbId, String userAccount);

}
