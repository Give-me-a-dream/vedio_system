package com.example.vediosystem.service;

import com.example.vediosystem.domain.User;
import com.example.vediosystem.domain.UserDetail;

public interface UserService {
    /**
     * 用户注册
     * @param
     */
    boolean register(User user);

    /**
     * 用户登录
     * @param  loginName 用户名
     * @param  password 用户密码
     * @return
     */
    User login(String loginName,String password);

    /**
     * 通过用户名查询用户
     * @param name
     * @return
     */
    User selectByName(String name);

    /**
     * 根据传入信息更新用户详细信息
     * @param userDetail 用户信息类
     */
    void updateUserDetail(UserDetail userDetail);

    /**
     * 获取用户信息
     * @param id 用户ID
     * @return 用户信息类
     */
    UserDetail getUserDetail(int id);
}
