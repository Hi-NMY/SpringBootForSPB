package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.CollectBarDto;
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

    @Select("SELECT user_account,pb_one_id " +
            "FROM ${dname}")
    List<CollectBarDto> queryCollectBarList(@Param("dname") String dname);

    //获取详细列表

    @Insert("INSERT INTO ${dname}(user_account,pb_one_id) " +
            "values(#{cache_Account},#{pb_one_id}")
    int addCollectBar(@Param("dname") String dname,@Param("cache_Account") String cacheAccount,@Param("pb_one_id") String pbId);

    @Delete("DELETE FROM ${dname} " +
            "where user_account = '{$cache_account}' AND pb_one_id = '{$pb_one_id}'")
    int deleteCollectBar(@Param("dname") String dname,@Param("cache_Account") String cacheAccount,@Param("pb_one_id") String pbId);
}
