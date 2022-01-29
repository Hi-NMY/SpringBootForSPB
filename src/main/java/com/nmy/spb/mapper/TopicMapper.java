package com.nmy.spb.mapper;

import com.nmy.spb.domain.pojo.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author nmy
 * @title: TopicMapper
 * @date 2022-01-22 13:46
 */
public interface TopicMapper {

    @Select("SELECT topic_name " +
            "FROM topic " +
            "ORDER BY id DESC LIMIT 20 ")
    List<String> queryTopicNameList();

    @Select("SELECT topic_name " +
            "FROM topic " +
            "WHERE topic_name like concat('%',#{topic_name},'%')")
    List<String> querySearchTopicNameList(@Param("topic_name") String topicName);

    @Select("SELECT id,topic_name,topic_barnum,topic_attentionnum,topic_slogan,topic_image " +
            "FROM topic " +
            "WHERE topic_barnum >= #{min} AND topic_barnum <= #{max} " +
            "ORDER BY topic_barnum DESC LIMIT 10")
    List<Topic> queryRundomTopicFullList(@Param("min") int min, @Param("max") int max);

    @Select("SELECT id,topic_name,topic_barnum,topic_attentionnum,topic_slogan,topic_image " +
            "FROM topic " +
            "WHERE topic_name = #{topic_name}")
    Topic queryTopicFull(@Param("topic_name") String topicName);

    @Select("SELECT id,topic_name,topic_barnum,topic_attentionnum,topic_slogan,topic_image " +
            "FROM topic " +
            "WHERE topic_name concat('%',#{topic_name},'%')")
    List<Topic> querySearchTopicFullList(@Param("topic_name") String topicName);

    @Update("UPDATE topic " +
            "set topic_attentionnum = topic_attentionnum + 1 where id = #{topic_id}")
    int updateIncreaseAttention(@Param("topic_id") int id);

    @Update("UPDATE topic " +
            "set topic_attentionnum = topic_attentionnum - 1 where id = #{topic_id}")
    int updateReduceAttention(@Param("topic_id") int id);

//    @Update("UPDATE topic " +
//            "set topic_barnum = topic_barnum + 1 where topic_name = #{topic_name}")
//    int updateIncreaseBarNum(@Param("topic_name") String topicName);

//    @Update("UPDATE topic " +
//            "set topic_barnum = topic_barnum - 1 where topic_name = #{topic_name}")
//    int updateReduceBarNum(@Param("topic_name") String topicName);

//    @Insert("insert into topic(topic_name,topic_barnum,topic_attentionnum,topic_slogan,topic_image) " +
//            "values(#{topic_name},1,0,'','')")
//    int addTopic(@Param("topic_name") String topicName);

    int updateIncreaseBarNum(@Param("list") List<String> topicName);
}
