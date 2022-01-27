package com.nmy.spb.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author nmy
 * @title: InitTableMapper
 * @date 2022-01-22 15:41
 */
public interface InitTableMapper {

    @Insert("insert into user(user_account,user_name,user_head_image,user_longday,user_privacy) " +
            "values(#{user_account},#{user_name},#{head_image},0,'11111112')")
    int addUserRegistered(@Param("user_account") String userAccount, @Param("user_name") String userName, @Param("head_image") String imagePath);

    @Insert("insert into user_sign(user_account,sign_coin,sign_right,sign_ct_badge) " +
            "values(#{user_account},0,1,1)")
    int addSignRegistered(@Param("user_account") String userAccount);

    @Insert("insert into users(user_account,user_password,user_secret_protection) " +
            "values(#{user_account},#{user_password},'111111111')")
    int addUsersRegistered(@Param("user_account") String userAccount, @Param("user_password") String userPassword);
}
