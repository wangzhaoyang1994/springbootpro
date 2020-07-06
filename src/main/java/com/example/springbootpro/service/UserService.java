package com.example.springbootpro.service;

import com.example.springbootpro.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();
    List<User> findUserByName(String name);
    int addUser(User user);
    int updateUser(String name,String role,String userimg,int id);
    List<Map<String,Object>> selectRoleAll();
}
