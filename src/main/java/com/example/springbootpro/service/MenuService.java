package com.example.springbootpro.service;

import com.example.springbootpro.entity.Menu;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {
    List<Menu> findAllMenu();
    JSONObject findAllMenuByParentIdAndRole(String userRole);
    JSONObject findAllMenuByPageS(int pageNum, int pageSize,String userRole);
    int addMenu(Menu menu);
    List<Menu> findAllMenuByParentId(int parentId);
    int findAllMenuByIds(String ids,String roleSure);
    int findAllMenuByNotIds(String ids,String roleSure);
}
