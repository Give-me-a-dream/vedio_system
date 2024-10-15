package com.example.vediosystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_favorites")
public class Favorites {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("u_id")
    private int userId;

    @TableField("name")
    private String name;
}
