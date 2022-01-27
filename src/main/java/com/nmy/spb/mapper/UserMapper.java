package com.nmy.spb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author nmy
 * @title: UserMapper
 * @date 2022-01-22 13:32
 */
public interface UserMapper {

    @Update("UPDATE user " +
            "set user_longday = user_longday + 1 " +
            "where user_account = #{user_account}")
    int updateUserLongDay(@Param("user_account") String userAccount);

}
