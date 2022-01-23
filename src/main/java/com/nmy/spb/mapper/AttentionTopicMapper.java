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

    @Insert("INSERT INTO ${dname}(topic_id,topic_name,topic_date) " +
            "values (#{topic_id},#{topic_name},#{topic_date})")
    int addAttentionTopic(@Param("dname") String dname,@Param("topic_id") String id,@Param("topic_name") String name,@Param("topic_date") String date);

    @Select("SELECT topic.id,${dname}.topic_date,topic.topic_name,topic.topic_barnum,topic.topic_attentionnum,topic.topic_slogan,topic.topic_image " +
            "FROM ${dname} left join topic " +
            "ON topic.id = ${dname}.topic_id " +
            "WHERE ${dname}.topic_date <= #{topic_date} " +
            "ORDER BY topic_date DESC")
    List<AttentionTopicDto> queryAttentionTopic(@Param("dname") String dname,@Param("topic_date") String date);

    @Delete("DELETE FROM ${dname} " +
            "where topic_id = #{topic_id}")
    int deleteAttentionTopicById(@Param("dname") String dname,@Param("topic_id") String id);
}
