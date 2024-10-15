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
@TableName("t_browsing_history")
public class History {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("u_id")
    private int userId;

    @TableField("vedio_id")
    private int videoId;

    @TableField("history_time")
    private int historyTime;

    @TableField("watch_time")
    private LocalDateTime watchTime;



}
