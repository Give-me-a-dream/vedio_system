package com.example.vediosystem.controller;

import com.example.vediosystem.exception.BusinessException;
import com.example.vediosystem.exception.SystemException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExcepionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){

        return new Result(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){

        return new Result(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result unauthorizedException(Exception e){
        return new Result(Code.UN_AUTHOR,"您无执行此操作的权限");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result authorizationException(Exception e){
        return new Result(Code.AUTHOR_ERR,"权限认证失败");
    }

//    @ExceptionHandler(Exception.class)
//    public Result doOtherException(Exception ex){
//
//        return new Result(Code.SYSTEM_UNKNOW_ERR,null,"系统出错啦！");
//    }

}
