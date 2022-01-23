package com.nmy.spb.mapper;

import com.nmy.spb.domain.pojo.Sign;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author nmy
 * @title: SignMapper
 * @date 2022-01-22 12:19
 */
public interface SignMapper {

    @Select("SELECT user_account,sign_coin,sign_right,sign_day,sign_star_badge,sign_task_badge,sign_like_badge,sign_ct_badge " +
            "FROM user_sign " +
            "WHERE user_account = #{user_account}")
    Sign queryUserSign(@Param("user_account") String userAccount);

    @Update("UPDATE user_sign " +
            "set sign_day = '' " +
            "where user_account = #{user_account}")
    int updateSignDay(@Param("user_account") String userAccount);

    @Update("UPDATE user_sign " +
            "set sign_right = 1 " +
            "where user_account = #{user_account}")
    int updateSignRight(@Param("user_account") String userAccount);

    @Update("UPDATE user_sign " +
            "set sign_right = 1,sign_day = '' " +
            "where user_account = #{user_account}")
    int updateSignDayAndRight(@Param("user_account") String userAccount);

    @Update("UPDATE user_sign " +
            "set sign_right = 0,sign_day = #{sign_day},sign_coin = sign_coin + #{coin} " +
            "where user_account = #{user_account}")
    int updateSignDayAndRightAndCoin(@Param("user_account") String userAccount,@Param("sign_day") String signDay,@Param("coin") int coin);

    @Update("UPDATE user_sign " +
            "set sign_coin = sign_coin + #{coin} " +
            "where user_account = #{user_account}")
    int updateSignCoin(@Param("user_account") String userAccount,@Param("coin") int coin);

    @Update("UPDATE user_sign " +
            "set sign_star_badge = #{sign_star_badge} " +
            "where user_account = #{user_account}")
    int updateSignStarBadge(@Param("user_account") String userAccount,@Param("sign_star_badge") String starBadge);

    @Update("UPDATE user_sign " +
            "set sign_like_badge = #{sign_like_badge} " +
            "where user_account = #{user_account}")
    int updateSignLikeBadge(@Param("user_account") String userAccount,@Param("sign_like_badge") String likeBadge);

    @Update("UPDATE user_sign " +
            "set sign_task_badge = #{sign_tesk_badge} " +
            "where user_account = #{user_account}")
    int updateSignTaskBadge(@Param("user_account") String userAccount,@Param("sign_tesk_badge") String taskBadge);

}
