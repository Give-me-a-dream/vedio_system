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
@TableName("t_vedio")
public class Video {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 视频名
     */
    @TableField("v_name")
    private String vName;

    /**
     * 上传者ID
     */
    @TableField("uploader_id")
    private int uploaderId;

    /**
     * 上传时间
     */
    @TableField("uploader_time")
    private LocalDateTime uploaderTime;

    /**
     * 视频存储地址
     */
    @TableField("link")
    private String link;

    /**
     * 播放量
     */
    @TableField("plays")
    private int plays;

    /**
     * 点赞数
     */
    @TableField("likes")
    private int likes;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 视频封面URL
     */
    @TableField("pic_url")
    private String picUrl;

    /**
     * 视频属地ID
     */
    @TableField("region_id")
    private int regionId;

}
