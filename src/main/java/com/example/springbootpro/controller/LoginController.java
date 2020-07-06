package com.example.springbootpro.controller;

import com.example.springbootpro.entity.User;
import com.example.springbootpro.service.UserService;
import com.example.springbootpro.utils.JsonUtils;
import com.example.springbootpro.utils.StringUtils;
import com.example.springbootpro.utils.sessionUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/register")
    public JSONObject login(String userName, String passWord, String verCode) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        boolean status = false;
        int error =0;//0 登陆成功 1 密码不正确 2 用户不存在 3 验证码不正确
        JSONObject result = new JSONObject();
        if (StringUtils.isNotEmpty("userName") && StringUtils.isNotEmpty("passWord")) {
            if (StringUtils.isNotEmpty("verCode")) {
                //验证码验证
                String code = (String) sessionUtils.get(request,"yzm");
                List<User> user = userService.findUserByName(userName);
                if (verCode.equalsIgnoreCase(code)) {
                    if (user.size() > 0) {
                        if (DigestUtils.md5DigestAsHex(passWord.getBytes()).equals(user.get(0).getPassword())) {
                            status = true;
                            result = JsonUtils.render(status, "登陆成功",0,user.get(0));
                        } else {
                            result = JsonUtils.renderNew(status, "密码不正确",1);
                        }
                    } else {
                        result = JsonUtils.renderNew(status, "用户不存在",2);
                    }
                } else {
                    result = JsonUtils.renderNew(status, "输入的验证码不正确",3);
                }

            }

        }

        return result;
    }
    /**
     * shiro 登录逻辑
     */
    @RequestMapping("/login")
    public String login(String name,String password){
        /**
         * 使用shiro编写认证操作
         */
        // 1.获取Subject
       Subject subject= SecurityUtils.getSubject();
       //2.封装用户数据
        UsernamePasswordToken token =new UsernamePasswordToken(name,password);
        //3.执行登陆方法
        //4.shiro怎样判断是登录成功还是失败？只要subject.login(token)没有任何异常就表示登录成功了
        try {
            subject.login(token);//执行这个login方法，会默认执行shiro--UserRealm--doGetAuthenticationInfo这个方法
            //登录成功
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            //登陆失败 :UnknownAccountException这个异常表示用户名不存在
        }catch (IncorrectCredentialsException e){
            //IncorrectCredentialsException这个异常表示密码错误
            e.printStackTrace();
        }
        return "";
    }
}
