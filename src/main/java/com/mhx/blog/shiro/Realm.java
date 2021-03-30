package com.mhx.blog.shiro;

import com.mhx.blog.Service.RootService;
import com.mhx.blog.domain.Root;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Realm extends AuthorizingRealm {

    @Autowired
    private RootService rootService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权");
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Root root = rootService.selectRootByName(token.getUsername());
        if (root == null) return null;
        logger.info("管理员"+root.getName()+"已登录");
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("root",root);
        return new SimpleAuthenticationInfo("",root.getPassword(),"");
    }
}
