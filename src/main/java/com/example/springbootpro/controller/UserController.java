package com.example.springbootpro.controller;

import com.example.springbootpro.entity.User;
import com.example.springbootpro.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAll")
    @CrossOrigin
    public List<User> findAll(){
        return userService.findAll();
    }
    @RequestMapping("/zhuce")
    public int addUser(User user,String userName,String passWord,String role){
        String md5PassWord = DigestUtils.md5DigestAsHex(passWord.getBytes());
        user.setName(userName);
        user.setPassword(md5PassWord);
        user.setRole(role);
        return  userService.addUser(user);
    }
    @RequestMapping("/updateUser")
    public int updateUser(String name, String role, String userimg, int id){
        if (role.equalsIgnoreCase("管理员")){
            role="admin";
        }else if (role.equalsIgnoreCase("教师")){
            role="teacher";
        }else {
            role="student";
        }
        return userService.updateUser(name,role,userimg,id);
    }
    @RequestMapping("selectRole")
    public List<Map<String,Object>> selectRole(){
        return userService.selectRoleAll();
    }
}
