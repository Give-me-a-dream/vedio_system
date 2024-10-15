package com.example.vediosystem.service.serviceImpl;

import com.example.vediosystem.controller.Code;
import com.example.vediosystem.dao.CommentDao;
import com.example.vediosystem.domain.Comment;
import com.example.vediosystem.domain.U_Comment;
import com.example.vediosystem.domain.User;
import com.example.vediosystem.exception.BusinessException;
import com.example.vediosystem.service.CommentService;
import com.example.vediosystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    UserService userService;

    @Override
    public U_Comment getComment(int id) {
        return commentDao.selectCommentById(id);
    }

    @Override
    public List<U_Comment> getHotComments(int videoId, int page) {
        return commentDao.selectHotComments(videoId, Code.LIMIT, (page - 1) * Code.LIMIT);
    }

    @Override
    public List<U_Comment> getNewComments(int videoId, int page) {
        return commentDao.selectNewComments(videoId, Code.LIMIT, (page - 1) * Code.LIMIT);
    }

    @Override
    public Integer getVideoCommentPage(int videoId) {
        int count = commentDao.getCommentCount(videoId);
        //向上取整
        return count / Code.LIMIT + (count % Code.LIMIT != 0 ? 1 : 0);
    }

    @Override
    public void addComment(Comment comment) {
        LocalDateTime now = LocalDateTime.now(); // 获取当前时间
        comment.setTime(now);

        int row = commentDao.insert(comment);
        if (row<1)throw new BusinessException(Code.SAVE_ERR,"保存数据失败");

    }

    @Override
    public void likeComment(int id) {
        int row = commentDao.likeComment(id);
        if (row<1)throw new  BusinessException(Code.UPDATE_ERR,"更新失败");
    }

    @Override
    public void dislikeComment(int id) {
        int row = commentDao.dislikeComment(id);
        if (row<1)throw new  BusinessException(Code.UPDATE_ERR,"更新失败");
    }


}
