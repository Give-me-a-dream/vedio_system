package com.example.vediosystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_vedio_sorts")
public class VideoSort {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 视频ID
     */
    @TableField("vedio_id")
    private Integer videoId;

    /**
     * 分类ID
     */
    @TableField("sort_id")
    private Integer sortId;
}
