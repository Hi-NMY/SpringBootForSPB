package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.BarDto;
import com.nmy.spb.domain.pojo.Bar;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author nmy
 * @title: PostBarMapper
 * @date 2022-01-26 19:42
 */
public interface PostBarMapper {

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_image_url,postbarlist.pb_voice,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE postbarlist.pb_date <= #{pb_date} AND postbarlist.pb_video IS NULL " +
            "ORDER BY postbarlist.pb_date DESC LIMIT 5")
    List<BarDto> queryNoVideoBarListForDate(@Param("pb_date") String pbDate);

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_image_url,postbarlist.pb_voice,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE postbarlist.pb_thumb_num <= #{pb_thumb_num} AND postbarlist.pb_video IS NULL AND postbarlist.pb_topic like concat('%',#{topic_name},'%') " +
            "ORDER BY postbarlist.pb_thumb_num DESC,postbarlist.pb_date DESC LIMIT 5")
    List<BarDto> queryNoVideoTopicBarListForThumbNum(@Param("pb_thumb_num") int thumbNum, @Param("topic_name") String topicName);

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_image_url,postbarlist.pb_voice,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE postbarlist.pb_date <= #{pb_date} AND postbarlist.pb_video IS NULL AND postbarlist.pb_topic like concat('%',#{topic_name},'%') " +
            "ORDER BY postbarlist.pb_date DESC LIMIT 5")
    List<BarDto> queryNoVideoTopicBarListForDate(@Param("pb_date") String pbDate, @Param("topic_name") String topicName);

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_image_url,postbarlist.pb_voice,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE postbarlist.user_account = #{user_account} AND postbarlist.pb_video IS NULL AND postbarlist.pb_date <= #{pb_date} " +
            "ORDER BY postbarlist.pb_date DESC LIMIT 5")
    List<BarDto> queryNoVideoUserBarListForDate(@Param("user_account") String userAccount, @Param("pb_date") String pbDate);

    @Select("SELECT count(id) as AllNum " +
            "FROM postbarlist " +
            "WHERE user_account = #{user_account}")
    int queryUserBarCount(@Param("user_account") String userAccount);

    @Select("SELECT u.user_account,u.user_name,u.user_badge,p.pb_one_id,p.pb_date " +
            ",p.pb_article,p.pb_image_url,p.pb_voice,p.pb_topic " +
            ",p.pb_location,p.pb_thumb_num,p.pb_comment_num " +
            "from follow f " +
            "left join postbarlist p " +
            "on f.followed_account = p.user_account " +
            "left join user u " +
            "on f.followed_account = u.user_account " +
            "WHERE f.follow_account = #{user_account} AND p.pb_video IS NULL AND p.pb_date <= #{pb_date} " +
            "ORDER BY p.pb_date DESC LIMIT 5")
    List<BarDto> queryNoVideoFollowBarListForDate(@Param("user_account") String userAccount, @Param("pb_date") String pbDate);

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_image_url,postbarlist.pb_voice,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE postbarlist.pb_one_id = #{pb_one_id}")
    BarDto queryBarDetatilForPbid(@Param("pb_one_id") String pbid);

    @Select("SELECT IFNULL(SUM(pb_thumb_num),0) " +
            "from postbarlist " +
            "WHERE user_account = #{user_account}")
    int queryUserBarLikeCount(@Param("user_account") String userAccount);

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_image_url,postbarlist.pb_voice,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE (postbarlist.pb_topic like concat('%',#{search_art},'%') OR postbarlist.pb_article like concat('%',#{search_art},'%')) AND postbarlist.pb_video IS NULL " +
            "ORDER BY postbarlist.pb_date DESC LIMIT 30")
    List<BarDto> queryNoVideoSearchBarListForDate(@Param("search_art") String searchArt);

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_video,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE postbarlist.pb_date <= #{pb_date} AND postbarlist.pb_video IS NOT NULL " +
            "ORDER BY postbarlist.pb_date DESC LIMIT 5")
    List<BarDto> queryVideoBarListForDate(@Param("pb_date") String pbDate);

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_video,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE postbarlist.pb_date <= #{pb_date} AND postbarlist.pb_video IS NOT NULL AND postbarlist.pb_topic like concat('%',#{topic_name},'%') " +
            "ORDER BY postbarlist.pb_date DESC LIMIT 2")
    List<BarDto> queryVideoTopicBarListForDate(@Param("pb_date") String pbDate, @Param("topic_name") String topicName);

    @Select("SELECT user.user_account,user.user_name,user.user_badge,postbarlist.pb_one_id,postbarlist.pb_date " +
            ",postbarlist.pb_article,postbarlist.pb_video,postbarlist.pb_topic " +
            ",postbarlist.pb_location,postbarlist.pb_thumb_num,postbarlist.pb_comment_num " +
            "from postbarlist " +
            "left join user " +
            "on postbarlist.user_account = user.user_account " +
            "WHERE postbarlist.user_account = #{user_account} AND postbarlist.pb_video IS NOT NULL AND postbarlist.pb_date <= #{pb_date} " +
            "ORDER BY postbarlist.pb_date DESC LIMIT 5")
    List<BarDto> queryVideoUserBarListForDate(@Param("user_account") String userAccount, @Param("pb_date") String pbDate);

    @Update("UPDATE postbarlist " +
            "set pb_thumb_num = pb_thumb_num + 1 " +
            "where pb_one_id = #{pb_one_id}")
    int updateIncreaseLike(@Param("pb_one_id") String pbid);

    @Update("UPDATE postbarlist " +
            "set pb_thumb_num = pb_thumb_num - 1 " +
            "where pb_one_id = #{pb_one_id}")
    int updateReduceLike(@Param("pb_one_id") String pbid);

    @Update("UPDATE postbarlist " +
            "set pb_comment_num = pb_comment_num + 1 " +
            "where pb_one_id = #{pb_one_id}")
    int updateIncreaseComment(@Param("pb_one_id") String pbid);

    @Update("UPDATE postbarlist " +
            "set pb_comment_num = pb_comment_num - 1 " +
            "where pb_one_id = #{pb_one_id}")
    int updateReduceComment(@Param("pb_one_id") String pbid);

    @Delete("DELETE FROM postbarlist " +
            "where pb_one_id = #{pb_one_id}")
    int deleteBar(@Param("pb_one_id") String pbid);

    @Insert("insert into postbarlist(pb_one_id,user_account,pb_date,pb_article" +
            ",pb_image_url,pb_voice,pb_video,pb_topic,pb_location,pb_thumb_num,pb_comment_num) " +
            "values(#{pb_one_id},#{user_account},#{pb_date},#{pb_article},#{pb_image_url},#{pb_voice},#{pb_video},#{pb_topic},#{pb_location},0,0)")
    int addBar(Bar bar);
}
