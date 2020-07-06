package com.example.springbootpro.service.impl;

import com.example.springbootpro.entity.User;
import com.example.springbootpro.mapper.UserMapper;
import com.example.springbootpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(String name, String role, String userimg, int id) {
        return userMapper.updateUser(name,role,userimg,id);
    }

    @Override
    public List<Map<String, Object>> selectRoleAll() {
        return userMapper.selectRoleAll();
    }
}
