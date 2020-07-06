package com.example.springbootpro.mapper;

import com.example.springbootpro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper //指定这是一个操作数据库的mapper
public interface UserMapper {
    List<User> findAll();
    List<User> findUserByName(@Param("name") String name);
    int addUser(User user);
    int updateUser(String name,String role,String userimg,int id);
    List<Map<String,Object>> selectRoleAll();
}
