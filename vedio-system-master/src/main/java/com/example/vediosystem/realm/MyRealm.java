package com.example.vediosystem.realm;

import com.example.vediosystem.controller.Code;
import com.example.vediosystem.domain.User;
import com.example.vediosystem.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // System.out.println("授权认证");
        //没做角色分类也就暂时没有授权认证
        return null;
    }

    //自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户身份信息
        String name = authenticationToken.getPrincipal().toString();
        //2.获取数据库中用户信息
        User user = userService.selectByName(name);
        //3.非空判断将数据返回
        if (user != null){
            return new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    user.getPassword(),
                    ByteSource.Util.bytes(Code.SALT),
                    authenticationToken.getPrincipal().toString()
            );
        }
        return null;
    }
}
