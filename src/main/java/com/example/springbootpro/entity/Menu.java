package com.example.springbootpro.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
    private int id;//菜单id
    private String menuName;//菜单名
    private int parentId;//父id
    private String userRole;//用户角色
    private List<Menu> children;//子菜单
    private String component;//前端组件

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getId() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getParentId() {
        return parentId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
