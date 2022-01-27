package com.nmy.spb.mapper;

import com.nmy.spb.domain.pojo.Diary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author nmy
 * @title: DiaryMapper
 * @date 2022-01-27 22:20
 */
public interface DiaryMapper {

    @Select("SELECT id,dia_date,dia_weather,dia_message,dia_image FROM diary " +
            "WHERE user_account = #{user_account} " +
            "ORDER BY dia_date DESC")
    List<Diary> queryDiary(@Param("user_account") String userAccount);

    @Insert("INSERT INTO diary(user_account,dia_date,dia_weather,dia_message,dia_image) " +
            "values(#{user_account},#{dia_date},#{dia_weather},#{dia_message},#{dia_image}) ")
    int addDiary(Diary diary);

    @Delete("DELETE FROM diary " +
            "where user_account = #{user_account} AND id = #{id}")
    int deleteDiary(@Param("user_account") String userAccount, @Param("id") int id);
}
