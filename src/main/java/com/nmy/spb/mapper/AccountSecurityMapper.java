package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author nmy
 * @title: AccountSecurityMapper
 * @date 2022-01-22 14:18
 */
public interface AccountSecurityMapper {

    @Select("SELECT IFNULL(MAX(id),-1) " +
            "FROM users " +
            "where user_account = #{user_account} AND user_password = #{user_password}")
    int queryUserExist(@Param("user_account") String userAccount,@Param("user_password") String userPassword);

    @Update("UPDATE users " +
            "SET user_password = #{user_password} " +
            "WHERE user_account = #{user_account}")
    int updateUserPassword(@Param("user_account") String userAccount,@Param("user_password") String userPassword);

    @Select("SELECT user.id,user.user_account,user.user_name,user.user_birth,user.user_home,user.user_favorite,user.user_profile,user.user_badge,user.user_longday,user.user_privacy,user.user_ip,user.user_token,students.stu_sex,students.stu_name " +
            "FROM user,users,students " +
            "where users.user_account = #{user_account} AND user.user_account = #{user_account} AND students.stu_num = #{user_account}")
    UserDto queryUserFull(@Param("user_account") String userAccount);

    @Select("select stu_num from students where stu_num = #{user_account}")
    String queryVerifyStu(@Param("user_account") String userAccount);

    @Select("select user_account from users where user_account = #{user_account}")
    String queryVerifyUser(@Param("user_account") String userAccount);

    @Select("select user_account from user where user_name = #{userName}")
    String queryVerifyUserName(@Param("user_name") String userName);
}
