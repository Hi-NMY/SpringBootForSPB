package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.BarDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author nmy
 * @title: CollectBarMapper
 * @date 2022-01-22 10:22
 */
public interface CollectBarMapper {

    @Select("SELECT pb_one_id " +
            "FROM collectbar " +
            "WHERE user_account = #{user_account}")
    List<String> queryCollectBarList(@Param("user_account") String userAccount);

    @Select("SELECT u.user_account,u.user_name,u.user_badge,p.pb_one_id,p.pb_date " +
            ",p.pb_article,p.pb_image_url,p.pb_voice,p.pb_video,p.pb_topic " +
            ",p.pb_location,p.pb_thumb_num,p.pb_comment_num " +
            "FROM collectbar c " +
            "LEFT JOIN postbarlist p ON c.pb_one_id = p.pb_one_id " +
            "LEFT JOIN user u ON p.user_account = u.user_account " +
            "WHERE c.user_account = #{user_account}")
    List<BarDto> queryCollectBarFullList(@Param("user_account") String userAccount);

    @Insert("INSERT INTO collectbar(user_account,pb_one_id) " +
            "select #{user_account},#{pb_one_id} " +
            "from dual " +
            "WHERE NOT EXISTS " +
            "(SELECT user_account,pb_one_id FROM collectbar WHERE user_account = #{user_account} AND pb_one_id = #{pb_one_id})")
    int addCollectBar(@Param("user_account") String userAccount, @Param("pb_one_id") String pbId);

    @Delete("DELETE FROM collectbar " +
            "where user_account = #{user_account} AND pb_one_id = #{pb_one_id}")
    int deleteCollectBar(@Param("user_account") String userAccount, @Param("pb_one_id") String pbId);
}
