package com.example.vediosystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_comments")
public class Comment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 视频ID
     */
    @TableField("vedio_id")
    private int videoId;

    /**
     * 评论内容
     */
    @TableField("comment")
    private String comment;

    /**
     * 评论时间
     */
    @TableField("time")
    private LocalDateTime time;

    /**
     * 点赞数
     */
    @TableField("likes")
    private int likes;

    /**
     * 点踩数
     */
    @TableField("unlikes")
    private int unlikes;

    /**
     * 评论者id
     */
    @TableField("user_id")
    private int userId;
}
