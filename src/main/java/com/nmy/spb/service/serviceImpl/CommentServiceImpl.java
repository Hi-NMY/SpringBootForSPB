package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.CommentDto;
import com.nmy.spb.mapper.CommentMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.CommentService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DataVerificationTool;
import com.nmy.spb.utils.DateTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nmy
 * @title: CommentServiceImpl
 * @date 2022-01-22 20:51
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;
    @Resource
    SqlResultService sqlResultService;
    @Resource
    UserIpMapper userIpMapper;

    @Override
    public String addComment(CommentDto comment) {
        comment.setComment_date(DateTool.obtainNowDateTime());
        int commentId = commentMapper.queryMaxCommentId(comment.getPb_one_id());
        commentId += 1;
        comment.setComment_id(commentId);

        int value = commentMapper.addComment(comment);

        if (value == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }

        String cacheAccount = comment.getCache_account();
        if (!DataVerificationTool.isEmpty(cacheAccount)) {
            String ip = userIpMapper.queryUserIp(cacheAccount);
            comment.setUser_ip(ip);
        }

        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_COMMENT, comment));
    }

    @Override
    public String queryCommentList(String pbId) {
        List<CommentDto> commentDtos = commentMapper.queryCommentList(pbId);
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, commentDtos));
    }

    @Override
    public String queryCommentOne(String pbId, String commentId) {
        if (DataVerificationTool.isEmpty(pbId, commentId)) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }

        CommentDto commentDto = commentMapper.queryCommentOne(pbId, commentId);
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, commentDto));
    }

    @Override
    public String deleteComment(String pbId, String commentId) {
        int value = commentMapper.deleteComment(pbId, commentId);
        if (value == SQLResultCode.ERROR) {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_COMMENT);
    }

}
