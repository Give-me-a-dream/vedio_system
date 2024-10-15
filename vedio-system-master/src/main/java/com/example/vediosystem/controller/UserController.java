package com.example.vediosystem.controller;

import com.example.vediosystem.domain.User;
import com.example.vediosystem.domain.UserDetail;
import com.example.vediosystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){

        boolean flag = userService.register(user);
        Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
        String msg = flag ? "注册成功" : "注册失败，请重试！";
        return new Result(code,msg);
    }

    /**
     * 用户登录
     * @param
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User l_user){
        User user = userService.login(l_user.getName(),l_user.getPassword());
        Integer code = user != null ? Code.LOGIN_OK : Code.LOGIN_ERR;
        String msg = user != null ? "登录成功" : "登录失败，请重试！";
        //登录成功则传一个用户ID的参数，失败则不传
        if (user==null){
            return new Result(code,msg);
        }
        return new Result(code,user.getId(),msg);
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(Code.LOGOUT_OK,"登出成功");
    }

    @PostMapping("/userDetail")
    public Result updateUserDetail(@RequestBody UserDetail userDetail){
        userService.updateUserDetail(userDetail);
        return new Result(Code.UPDATE_OK,"更新成功");
    }

    @GetMapping("/getDetail/{id}")
    public Result getUserDetail(@PathVariable int id){
        UserDetail userDetail = userService.getUserDetail(id);
        return new Result(Code.GET_OK,userDetail,"获取成功");
    }
}
