package com.example.vediosystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vediosystem.domain.Comment;
import com.example.vediosystem.domain.U_Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentDao extends BaseMapper<Comment> {

    // 查询一条评论
@Select("""
    SELECT t_comments.id, 
           t_comments.vedio_id AS videoId, 
           t_comments.comment, 
           t_comments.time, 
           t_comments.likes, 
           t_comments.unlikes, 
           t_user.name
    FROM t_comments
    JOIN t_user ON t_comments.user_id = t_user.id
    WHERE t_comments.id = #{id}
    """)
U_Comment selectCommentById(@Param("id") int id);

// 分页查询最热评论
@Select("""
    SELECT t_comments.id, 
           t_comments.vedio_id AS videoId, 
           t_comments.comment, 
           t_comments.time, 
           t_comments.likes, 
           t_comments.unlikes, 
           t_user.name
    FROM t_comments
    JOIN t_user ON t_comments.user_id = t_user.id
    WHERE t_comments.vedio_id = #{videoId}
    ORDER BY t_comments.likes DESC
    LIMIT #{limit} OFFSET #{offset}
    """)
List<U_Comment> selectHotComments(@Param("videoId") int videoId, @Param("limit") int limit, @Param("offset") int offset);

// 分页查询最新评论
@Select("""
    SELECT t_comments.id, 
           t_comments.vedio_id AS videoId, 
           t_comments.comment, 
           t_comments.time, 
           t_comments.likes, 
           t_comments.unlikes, 
           t_user.name
    FROM t_comments
    JOIN t_user ON t_comments.user_id = t_user.id
    WHERE t_comments.vedio_id = #{videoId}
    ORDER BY t_comments.time DESC
    LIMIT #{limit} OFFSET #{offset}
    """)
List<U_Comment> selectNewComments(@Param("videoId") int videoId, @Param("limit") int limit, @Param("offset") int offset);

    //查询视频评论数
    @Select("SELECT COUNT(*) FROM t_comments WHERE t_comments.vedio_id = #{videoId}")
    int getCommentCount(@Param("videoId") int videoId);

    //给指定ID的评论点赞
    @Update("UPDATE t_comments SET likes = likes+1 WHERE `id` = #{id};")
    int likeComment(@Param("id") int id);

    //给指定ID的评论点踩
    @Update("UPDATE t_comments SET unlikes = unlikes+1 WHERE `id` = #{id};")
    int dislikeComment(@Param("id") int id);
}
