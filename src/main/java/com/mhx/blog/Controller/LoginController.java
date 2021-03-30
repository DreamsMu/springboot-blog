package com.mhx.blog.Controller;

import com.mhx.blog.Service.RootService;
import com.mhx.blog.domain.Root;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private RootService rootService;

    @PostMapping("signIn")
    public Map signIn(String username, String password){
        HashMap<Object, Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            map.put("code","200");
            map.put("url","adminIndex");
        }catch (UnknownAccountException e){
            map.put("msg","用户名错误");
        }catch (IncorrectCredentialsException e){
            map.put("msg","密码错误");
        }
        return map;
    }

    @GetMapping("loginVerifyUsername/{username}")
    public Map signIn(@PathVariable("username") String username){
        HashMap<Object, Object> map = new HashMap<>();
        Root root = rootService.selectRootByName(username);
        if (root == null) map.put("code","100");
        return map;
    }
}
