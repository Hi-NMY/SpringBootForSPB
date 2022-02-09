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
            "FROM likepb " +
            "WHERE user_account = #{user_account}")
    List<Like> queryLike(@Param("user_account") String userAccount);

    @Insert("INSERT INTO likepb(user_account,pb_one_id) " +
            "select #{user_account},#{pb_one_id} " +
            "from dual " +
            "WHERE NOT EXISTS " +
            "(SELECT user_account,pb_one_id FROM likepb WHERE user_account = #{user_account} AND pb_one_id = #{pb_one_id})")
    int addLike(@Param("user_account") String userAccount, @Param("pb_one_id") String pbId);

    @Delete("DELETE FROM likepb " +
            "WHERE user_account = #{user_account} AND pb_one_id = #{pb_one_id}")
    int deleteLike(@Param("user_account") String userAccount, @Param("pb_one_id") String pbId);
}
