package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.mapper.LikeMapper;
import com.nmy.spb.service.LikeService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DatabasesTableNameTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author nmy
 * @title: LikeServiceImpl
 * @date 2022-01-23 11:46
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    LikeMapper likeMapper;

    @Resource
    SqlResultService sqlResultService;

    @Override
    public String queryLike(String userAccount) {
        return sqlResultService.process(likeMapper.queryLike(DatabasesTableNameTool.getLikeName(userAccount)));
    }

    @Override
    public String addLike(String pbId, String userAccount) {
        //修改postbar数据Mapper
        return sqlResultService.noProcess(likeMapper.addLike(DatabasesTableNameTool.getLikeName(userAccount),pbId));
    }

    @Override
    public String deleteLike(String pbId, String userAccount) {
        //修改postbar数据Mapper
        return sqlResultService.noProcess(likeMapper.deleteLike(DatabasesTableNameTool.getLikeName(userAccount),pbId));
    }
}
