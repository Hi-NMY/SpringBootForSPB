package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.RequestResultCode;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.CommentDto;
import com.nmy.spb.mapper.CommentMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.CommentService;
import com.nmy.spb.service.SqlResultService;
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
        int commentId = commentMapper.queryMaxCommentId(comment.getPb_one_id());
        commentId += 1;

        comment.setComment_date(DateTool.obtainNowDateTime());
        comment.setComment_id(commentId);

        int value = commentMapper.addComment(comment);

        if (value == SQLResultCode.ERROR) {
            return RequestResultCode.ERROR;
        }

        String ip = userIpMapper.queryUserIp(comment.getCache_account());

        return RequestResultCode.SUCCEES + toData(comment, ip);
    }

    @Override
    public String queryCommentList(String pbId) {
        List<CommentDto> commentDtos = commentMapper.queryCommentList(pbId);
        return sqlResultService.process(commentDtos);
    }

    @Override
    public String queryCommentOne(String pbId, String commentId) {
        CommentDto commentDto = commentMapper.queryCommentOne(pbId, commentId);
        return sqlResultService.process(commentDto);
    }

    @Override
    public String deleteComment(String pbId, String commentId) {
        int value = commentMapper.deleteComment(pbId, commentId);
        return sqlResultService.noProcess(value);
    }

    private String toData(CommentDto commentDtos, String ip) {
        return commentDtos.getPb_one_id() + "|" + commentDtos.getComment_art()
                + "|" + commentDtos.getComment_user() + "|" + commentDtos.getComment_touser() + "|" + commentDtos.getComment_id() + "&" + ip;
    }
}
