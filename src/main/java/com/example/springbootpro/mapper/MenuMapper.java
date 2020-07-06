package com.example.springbootpro.mapper;

import com.example.springbootpro.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MenuMapper {
     List<Menu> findAllMenu();
     List<Menu> findAllMenuByParentId(int parentId);
     List<Menu> findAllMenuByParentIdAndRole(int parentID,String userRole);
     int addMenu(Menu menu);
     List<Menu> findAllMenuByIds(List<String> ids);
     List<Menu> findAllMenuByNotIds(List<String> ids);
     int updateMenuRole(@Param("userrole")String userrole,@Param("id")int id);
}
