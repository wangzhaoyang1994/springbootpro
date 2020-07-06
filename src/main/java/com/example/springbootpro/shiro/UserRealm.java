package com.example.springbootpro.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro逻辑，判断用户名和密码(就是和数据库中的用户名，密码比较)
        //假设数据库的用户名和密码
        String name="王昭洋";
        String password="123456";
        //获取用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //获取用户的用户名
        String userName=token.getUsername();
        //判断密码
        //new SimpleAuthenticationInfo("",password,"");//其中password是数据库中用户的密码
        return null;
    }
}
