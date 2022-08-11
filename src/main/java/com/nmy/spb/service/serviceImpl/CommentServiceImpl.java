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
import com.soft.spb.pojo.entity.PostbarComment;
import com.soft.spb.pojo.vo.PostbarCommentVo;
import com.soft.spb.service.PostbarCommentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @DubboReference
    PostbarCommentService postbarCommentService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addComment(CommentDto comment) {
        PostbarComment postbarComment = new PostbarComment();
        postbarComment.setCommentArt(comment.getComment_art());
        postbarComment.setCommentTouser(comment.getComment_touser());
        postbarComment.setCommentUser(comment.getComment_user());
        postbarComment.setPbOneId(comment.getPb_one_id());
        postbarComment.setUserName(comment.getUser_name());
        postbarComment.setUserToname(comment.getUser_toname());
        PostbarCommentVo postbarCommentVo = postbarCommentService.addComment(postbarComment);
        if (postbarCommentVo != null) {
            comment.setComment_id(postbarCommentVo.getCommentId());
            comment.setComment_date(postbarCommentVo.getCommentDate().toString());
            String cacheAccount = comment.getCache_account();
            if (!DataVerificationTool.isEmpty(cacheAccount)) {
                String ip = userIpMapper.queryUserIp(cacheAccount);
                comment.setUser_ip(ip);
            }
            return sqlResultService.process(new RequestEntityJson<>(EnumCode.SUCCESS_COMMENT, comment));
        }else {
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
    }

    @Override
    public String queryCommentList(String pbId) {
        List<PostbarCommentVo> postbarCommentVos = postbarCommentService.queryCommentList(pbId);
        ArrayList<CommentDto> coms = new ArrayList<>();
        for (PostbarCommentVo vos : postbarCommentVos) {
            CommentDto com = new CommentDto();
            com.setComment_art(vos.getCommentArt());
            com.setComment_date(vos.getCommentDate().toString());
            com.setComment_id(vos.getCommentId());
            com.setComment_touser(vos.getCommentTouser());
            com.setComment_user(vos.getCommentUser());
            com.setPb_one_id(vos.getPbOneId());
            com.setUser_name(vos.getUserName());
            com.setUser_toname(vos.getUserToname());
            coms.add(com);
        }
        return sqlResultService.process(new RequestListJson<>(EnumCode.SUCCESS_DEFAULT, coms));
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

    @Override
    public String deleteComment(String pbId, String commentId) {
        PostbarComment postbarComment = new PostbarComment();
        postbarComment.setPbOneId(pbId);
        postbarComment.setCommentId(Integer.parseInt(commentId));
        Boolean aBoolean = postbarCommentService.deleteComment(postbarComment);
        if (!aBoolean){
            return sqlResultService.noProcess(EnumCode.ERROR_DEFAULT);
        }
        return sqlResultService.noProcess(EnumCode.SUCCESS_DELETE_COMMENT);
    }

}
