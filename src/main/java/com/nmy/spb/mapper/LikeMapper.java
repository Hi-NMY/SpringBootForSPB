package com.nmy.spb.mapper;

import com.nmy.spb.domain.pojo.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author nmy
 * @title: LikeMapper
 * @date 2022-01-22 12:13
 */
public interface LikeMapper {

    @Select("SELECT id,pb_one_id " +
            "FROM ${dname}")
    List<Like> queryLike(@Param("dname")String dname);

    @Insert("INSERT INTO ${dname}(pb_one_id) " +
            "values(#{pb_one_id})")
    int addLike(@Param("dname")String dname,@Param("pb_one_id")String pbId);

    @Delete("DELETE FROM ${dname} " +
            "where pb_one_id = #{pb_one_id}")
    int deleteLike(@Param("dname")String dname,@Param("pb_one_id")String pbId);
}
