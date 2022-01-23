package com.nmy.spb.service;

/**
 * @author nmy
 * @title: SignService
 * @date 2022-01-23 13:17
 */
public interface SignService {

    String queryUserSign(String userAccount);

    String updateSignDay(String userAccount);

    String updateSignRight(String userAccount);

    String updateSignDayAndRight(String userAccount);

    String updateSignDayAndRightAndCoin(String userAccount, String signDay, String coin);

    String updateSignCoin(String userAccount, String coin);

    String updateSignStarBadge(String userAccount, String starBadge);

    String updateSignLikeBadge(String userAccount, String likeBadge);

    String updateSignTaskBadge(String userAccount, String taskBadge);

}
