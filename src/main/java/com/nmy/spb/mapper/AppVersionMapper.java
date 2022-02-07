package com.nmy.spb.mapper;

import com.nmy.spb.domain.dto.AppVersionDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author nmy
 * @title: AppVersionMapper
 * @date 2022-02-07 17:14
 */
public interface AppVersionMapper {

    @Select("SELECT versionCode,versionName,detailed" +
            "FROM app_version" +
            "WHERE id = 1")
    AppVersionDto isVersion(@Param("versionCode") String versionCode);

}
