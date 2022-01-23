package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.FollowUserDto;
import com.nmy.spb.domain.pojo.Follow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author nmy
 * @title: FolloedMapper
 * @date 2022-01-22 11:56
 */
public interface FollowedMapper {

    @Select("SELECT id,user_account " +
            "FROM ${dname} " +
            "ORDER BY id DESC")
    List<Follow> queryFollowedList(@Param("dname") String dname);

    @Select("SELECT ${dname}.id,user.user_account,user.user_name,user.user_badge,students.stu_sex " +
            "FROM (${dname} left join user on ${dname}.user_account = user.user_account) " +
            "left join students " +
            "on ${dname}.user_account = students.stu_num " +
            "ORDER BY ${dname}.id DESC")
    List<FollowUserDto> queryFollowedUserList(@Param("dname") String dname);

    @Insert("INSERT into ${dname}(user_account) " +
            "values(#{user_account})")
    int addFollowed(@Param("dname") String dname , @Param("user_account") String userAccount);

    @Delete("DELETE FROM ${dname} " +
            "where user_account = #{user_account}")
    int deleteFollowed(@Param("dname") String dname , @Param("user_account") String userAccount);
}
