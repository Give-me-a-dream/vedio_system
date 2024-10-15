package com.example.vediosystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user_detail")
public class UserDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("realname")
    private String realName;

    @TableField("age")
    private int age;

    @TableField("address")
    private String address;

    @TableField("sex")
    private String sex;
}
