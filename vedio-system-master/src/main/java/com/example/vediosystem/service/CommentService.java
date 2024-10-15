package com.example.vediosystem.service;

import com.example.vediosystem.domain.Comment;
import com.example.vediosystem.domain.U_Comment;

import java.util.List;

public interface CommentService {
    /**
     * 通过ID，获得一条评论
     * @param id 评论ID
     * @return 用户评论类
     */
    public U_Comment getComment(int id);

    /**
     * 分页查询最热评论
     * @param videoId 视频ID
     * @param page 第几页
     * @return 多个评论实体类
     */
    public List<U_Comment> getHotComments(int videoId, int page);
    /**
     * 分页查询最新评论
     * @param videoId 视频ID
     * @param page 第几页
     * @return 多个评论实体类
     */
    public List<U_Comment> getNewComments(int videoId,int page);

    /**
     * 获得视频评论页数（分页查询，一页10条)
     * @param videoId 视频ID
     * @return 页数
     */
    public Integer getVideoCommentPage(int videoId);

    /**
     * 新增一条评论
     * @param comment 评论实体类
     */
    public void addComment(Comment comment);

    /**
     * 点赞评论
     * @param id 评论ID
     */
    public void likeComment(int id);

    /**
     * 点踩评论
     * @param id 评论ID
     */
    public void dislikeComment(int id);
}
