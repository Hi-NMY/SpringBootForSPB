package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.AttentionTopicDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author nmy
 * @title: AttentionTopicMapper
 * @date 2022-01-22 9:59
 */

public interface AttentionTopicMapper {

    @Insert("INSERT INTO attentiontopic(user_account,topic_id,topic_name,topic_date) " +
            "select #{user_account},#{topic_id},#{topic_name},#{topic_date} " +
            "from dual " +
            "WHERE NOT EXISTS " +
            "(SELECT id FROM attentiontopic WHERE user_account = #{user_account} AND topic_id = #{topic_id})")
    int addAttentionTopic(@Param("user_account") String userAccount, @Param("topic_id") String id, @Param("topic_name") String name, @Param("topic_date") String date);

    @Select("SELECT topic.id,attentiontopic.topic_date,topic.topic_name,topic.topic_barnum,topic.topic_attentionnum,topic.topic_slogan,topic.topic_image " +
            "FROM attentiontopic left join topic " +
            "ON topic.id = attentiontopic.topic_id " +
            "WHERE attentiontopic.user_account = #{user_account} AND attentiontopic.topic_date <= #{topic_date} " +
            "ORDER BY topic_date DESC")
    List<AttentionTopicDto> queryAttentionTopic(@Param("user_account") String userAccount, @Param("topic_date") String date);

    @Delete("DELETE FROM attentiontopic " +
            "where user_account = #{user_account} AND topic_id = #{topic_id}")
    int deleteAttentionTopicById(@Param("user_account") String userAccount, @Param("topic_id") String id);
}
