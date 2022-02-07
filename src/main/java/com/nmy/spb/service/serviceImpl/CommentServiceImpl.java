package com.nmy.spb.service.serviceImpl;

import com.nmy.spb.common.EnumCode;
import com.nmy.spb.common.RequestEntityJson;
import com.nmy.spb.common.RequestListJson;
import com.nmy.spb.common.SQLResultCode;
import com.nmy.spb.domain.dto.CommentDto;
import com.nmy.spb.mapper.CommentMapper;
import com.nmy.spb.mapper.PostBarMapper;
import com.nmy.spb.mapper.UserIpMapper;
import com.nmy.spb.service.CommentService;
import com.nmy.spb.service.SqlResultService;
import com.nmy.spb.utils.DataVerificationTool;
import com.nmy.spb.utils.DateTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    PostBarMapper postBarMapper;
    @Resource
    SqlResultService sqlResultService;
    @Resource
    UserIpMapper userIpMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addComment(CommentDto comment) {
        try {
            comment.setComment_date(DateTool.obtainNowDateTime());
            int commentId = commentMapper.queryMaxCommentId(comment.getPb_one_id());
            commentId += 1;
            comment.setComment_id(commentId);

            int ai = commentMapper.addComment(comment);
            int bi = postBarMapper.updateIncreaseComment(comment.getPb_one_id());
            if (!sqlResultService.transactionalProcess(ai, bi)) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }

            String cacheAccount = comment.getCache_account();
            if (!DataVerificationTool.isEmpty(cacheAccount)) {
                String ip = userIpMapper.queryUserIp(cacheAccount);
                comment.setUser_ip(ip);
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
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
        if (commentDto == null){
            return sqlResultService.noProcess(EnumCode.ERROR_COMMENT_ONE);
        }
        return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_DEFAULT, commentDto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteComment(String pbId, String commentId) {
        try {
            int ai = commentMapper.deleteComment(pbId, commentId);
            int bi = postBarMapper.updateReduceComment(pbId);
            if (!sqlResultService.transactionalProcess(ai, bi)) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
            }
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_COMMENT);
    }

}
