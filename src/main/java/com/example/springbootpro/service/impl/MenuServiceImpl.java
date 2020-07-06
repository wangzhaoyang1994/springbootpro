package com.example.springbootpro.service.impl;

import com.example.springbootpro.entity.Menu;
import com.example.springbootpro.mapper.MenuMapper;
import com.example.springbootpro.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service("MenuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    @Override
    public JSONObject findAllMenuByParentIdAndRole(String userRole) {
        JSONObject  allMenuList = new JSONObject();
        List<Menu> parentMenu = menuMapper.findAllMenuByParentIdAndRole(0,userRole);
        for (int i=0;i<parentMenu.size();i++){
            List<Menu> childrenMenu=menuMapper.findAllMenuByParentIdAndRole(parentMenu.get(i).getId(),userRole);
            parentMenu.get(i).setChildren(childrenMenu);
        }
        allMenuList.put("menuList",parentMenu);
        return allMenuList;
    }

    @Override
    public JSONObject findAllMenuByPageS(int pageNum, int pageSize,String userRole) {
        JSONObject pageMenu =new JSONObject();
        PageHelper.startPage(pageNum, pageSize);//写在查询list的语句之前
        List<Menu> parentMenu = menuMapper.findAllMenuByParentIdAndRole(0,userRole);
        for (int i=0;i<parentMenu.size();i++){
            List<Menu> childrenMenu=menuMapper.findAllMenuByParentIdAndRole(parentMenu.get(i).getId(),userRole);
            parentMenu.get(i).setChildren(childrenMenu);
        }
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(parentMenu);
        pageMenu.put("menuList",pageInfo);
        return  pageMenu;
    }

    @Override
    public int addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public List<Menu> findAllMenuByParentId(int parentId) {
        List<Menu> parentMenu = menuMapper.findAllMenuByParentId(parentId);
        for (int i=0;i<parentMenu.size();i++){
            List<Menu> childrenMenu=menuMapper.findAllMenuByParentId(parentMenu.get(i).getId());
            parentMenu.get(i).setChildren(childrenMenu);
        }
        return parentMenu;
    }

    @Override
    public int findAllMenuByIds(String ids,String roleSure) {
        int result = 0;
        List<String> newIds = Arrays.asList(ids.split(","));
        List<Menu> byIds = menuMapper.findAllMenuByIds(newIds);
        if (byIds.size() > 0){
            for (Menu menu : byIds) {
                if (menu.getUserRole() != null) {
                    String newStr ="";
                    if (menu.getUserRole().indexOf(roleSure) > -1) { //如果当前菜单包含角色
                        newStr=menu.getUserRole();
                    } else {
                        String acceptRole = menu.getUserRole();
                        if (!acceptRole.equals("") && acceptRole!=null){
                            newStr=acceptRole+","+roleSure;
                        }else {
                            newStr =roleSure;
                        }
                    }
                        result=menuMapper.updateMenuRole(newStr,menu.getId());
                }
            }
    }
        return result;
    }

    @Override
    public int findAllMenuByNotIds(String ids,String roleSure) {
        int result = 0;
        List<String> newIds = Arrays.asList(ids.split(","));
        List<Menu> notByIds = menuMapper.findAllMenuByNotIds(newIds);
        if (notByIds.size()>0){
            for (Menu menu : notByIds) {
                if (menu.getUserRole() != null) {
                    String newStr ="";
                    if (menu.getUserRole().indexOf(roleSure) > -1) { //如果当前菜单包含角色
                        String[] role=menu.getUserRole().split(",");
                        List<String> list=Arrays.asList(role);
                        if (list.contains(roleSure)){
                            List<String> arrayList=new ArrayList<String>(list);
                            arrayList.remove(roleSure);
                            String newStrBefore=arrayList.toString();
                            newStr=newStrBefore.substring(1,newStrBefore.length()-1).replaceAll(" ","");
                        }
                    } else {
                        newStr=menu.getUserRole();
                    }
                    result=menuMapper.updateMenuRole(newStr,menu.getId());
                }
            }
        }
        return result;
    }
}
