package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.SearchUserDto;
import com.nmy.spb.domain.dto.UserInformationDto;
import com.nmy.spb.domain.pojo.SchoolTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author nmy
 * @title: UserMapper
 * @date 2022-01-22 13:32
 */
public interface UserMapper {


    @Select("SELECT class_id,class_name,class_room,class_teacher,class_date,class_num,class_time_start,class_time_over" +
            " FROM 2018r184")
    List<SchoolTable> querySchoolTable();

    @Select("SELECT user.user_account,user.user_name,user.user_badge,students.stu_sex " +
            "FROM user " +
            "left join students " +
            "on user.user_account = students.stu_num " +
            "WHERE user.user_name like concat('%',#{search},'%') OR user.user_account like concat('%',#{search},'%')")
    List<SearchUserDto> querySearchUser(@Param("search") String search);

    @Update("UPDATE user " +
            "SET user_name = #{user_name},user_birth = #{user_birth},user_favorite = #{user_favorite}," +
            "user_home = #{user_home},user_profile = #{user_profile} " +
            "WHERE user_account = #{user_account}")
    int updateUserPersonalInformation(UserInformationDto info);

    @Update("UPDATE user " +
            "set user_head_image = #{user_head_image} " +
            "where user_account = #{user_account}")
    int updateUserHeadImage(@Param("user_head_image") String userHeadImage, @Param("user_account") String userAccount);

    @Update("UPDATE user " +
            "set user_bg_image = #{user_bg_image} " +
            "where user_account = #{user_account}")
    int updateUserBgImage(@Param("user_bg_image") String userHeadImage, @Param("user_account") String userAccount);

    @Update("UPDATE user " +
            "set user_badge = #{user_badge} " +
            "where user_account = #{user_account}")
    int updateUserBadgeImage(@Param("user_badge") String userBadge, @Param("user_account") String userAccount);

    @Update("UPDATE user " +
            "set user_privacy = #{user_privacy} " +
            "where user_account = #{user_account}")
    int updateUserPrivacy(@Param("user_privacy") String userPrivacy, @Param("user_account") String userAccount);

    @Update("UPDATE user " +
            "set user_longday = user_longday + 1 " +
            "where user_account = #{user_account}")
    int updateUserLongDay(@Param("user_account") String userAccount);

    @Delete("UPDATE user " +
            "SET user_ip = '' " +
            "WHERE user_account = #{user_account}")
    int deleteUserIp(@Param("user_account") String userAccount);
}
