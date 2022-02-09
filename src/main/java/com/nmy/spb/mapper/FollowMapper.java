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
 * @title: FollowMapper
 * @date 2022-01-22 11:49
 */
public interface FollowMapper {

    @Select("SELECT followed_account " +
            "FROM follow " +
            "WHERE follow_account = #{follow_account}" +
            "ORDER BY id DESC")
    List<String> queryFollowList(@Param("follow_account") String account);

    @Select("SELECT IFNULL(count(id),0) " +
            "FROM follow " +
            "WHERE follow_account = #{follow_account}")
    String queryFollowCount(@Param("follow_account") String account);

    @Select("SELECT follow.id,user.user_account,user.user_name,user.user_badge,students.stu_sex " +
            "FROM (follow left join user on follow.followed_account = user.user_account) " +
            "left join students " +
            "on follow.followed_account = students.stu_num " +
            "WHERE follow.follow_account = #{follow_account}" +
            "ORDER BY follow.id DESC")
    List<FollowUserDto> queryFollowUserList(@Param("follow_account") String account);

    @Insert("INSERT into follow(follow_account,followed_account) " +
            "select #{follow_account},#{followed_account} " +
            "from dual " +
            "WHERE NOT EXISTS " +
            "(SELECT follow_account,followed_account FROM follow WHERE follow_account = #{follow_account} AND followed_account = #{followed_account})")
    int addFollow(@Param("follow_account") String followAccount, @Param("followed_account") String followedAccount);

    @Delete("DELETE FROM follow " +
            "where follow_account = #{follow_account} AND followed_account = #{followed_account}")
    int deleteFollow(@Param("follow_account") String followAccount, @Param("followed_account") String followedAccount);

    @Select("SELECT follow_account " +
            "FROM follow " +
            "WHERE followed_account = #{followed_account}" +
            "ORDER BY id DESC")
    List<String> queryFollowedList(@Param("followed_account") String account);

    @Select("SELECT IFNULL(count(id),0) " +
            "FROM follow " +
            "WHERE followed_account = #{followed_account}")
    int queryFollowedCount(@Param("followed_account") String account);

    @Select("SELECT follow.id,user.user_account,user.user_name,user.user_badge,students.stu_sex " +
            "FROM (follow left join user on follow.follow_account = user.user_account) " +
            "left join students " +
            "on follow.follow_account = students.stu_num " +
            "WHERE follow.followed_account = #{followed_account}" +
            "ORDER BY follow.id DESC")
    List<FollowUserDto> queryFollowedUserList(@Param("followed_account") String account);
}
