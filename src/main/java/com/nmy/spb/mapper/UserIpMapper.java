package com.nmy.spb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author nmy
 * @title: UserIpMapper
 * @date 2022-01-22 10:43
 */
public interface UserIpMapper {

    @Select("SELECT IFNULL(user_ip,'') FROM user WHERE user_account = #{cache_account}")
    String queryUserIp(@Param("cache_account") String cacheAccount);

    @Update("UPDATE user " +
            "SET user_ip = #{userIp} " +
            "WHERE user_account = #{user_account}")
    int updateUserIp(@Param("user_account")String userAccount,@Param("user_ip")String userIp);
}
