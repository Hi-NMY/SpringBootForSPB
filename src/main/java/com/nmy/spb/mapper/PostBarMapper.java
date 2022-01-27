package com.nmy.spb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author nmy
 * @title: PostBarMapper
 * @date 2022-01-26 19:42
 */
public interface PostBarMapper {

    @Update("UPDATE postbarlist " +
            "set pb_thumb_num = pb_thumb_num + 1 " +
            "where pb_one_id = #{pb_one_id}")
    int updateIncreaseLike(@Param("pb_one_id") String pbid);

    @Update("UPDATE postbarlist " +
            "set pb_thumb_num = pb_thumb_num - 1 " +
            "where pb_one_id = #{pb_one_id}")
    int updateReduceLike(@Param("pb_one_id") String pbid);
}
