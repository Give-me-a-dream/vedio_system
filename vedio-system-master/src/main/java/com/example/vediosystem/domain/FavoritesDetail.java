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
@TableName("t_favorites_detail")
public class FavoritesDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("favorites_id")
    private int favoritesId;

    @TableField("vedio_id")
    private int videoId;

    @TableField("add_time")
    private LocalDateTime addTime;
}

