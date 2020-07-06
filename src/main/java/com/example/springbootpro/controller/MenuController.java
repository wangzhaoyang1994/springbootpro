package com.example.springbootpro.controller;

import com.example.springbootpro.entity.Menu;
import com.example.springbootpro.service.MenuService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("findAllMenu")
    public List<Menu> findAllMenu(){
        return menuService.findAllMenuByParentId(0);
    }
    @RequestMapping("/menuList")
    public JSONObject menuList(String role) {
        return menuService.findAllMenuByParentIdAndRole(role);
    }
    @RequestMapping("/addMenu")
    public int addMenu(Menu menu,String menuname,String userrole,int parentId,String component){
        menu.setMenuName(menuname);
        menu.setUserRole(userrole);
        menu.setParentId(parentId);
        menu.setComponent(component);
        return menuService.addMenu(menu);
    }
    @RequestMapping("/menuListByPage")
    public JSONObject menuPageInfo(int pageNum, int pageSize,String role){
        return menuService.findAllMenuByPageS(pageNum,pageSize,role);
    }
    @PostMapping("/menuSure/{ids}/{roleSure}")
    public JSONObject menuSure(@PathVariable("ids") String ids,@PathVariable("roleSure") String roleSure){
        JSONObject jsonObject =new JSONObject();
        int resultByIds = menuService.findAllMenuByIds(ids, roleSure);
        int resultNotByIds = menuService.findAllMenuByNotIds(ids,roleSure);
        if (ids.equals("0")){
            if (resultNotByIds == 1){
                jsonObject.put("result",1);
                jsonObject.put("msg","权限分配成功");
            }
        }
        if(resultByIds == 1 && resultNotByIds == 1){
            jsonObject.put("result",1);
            jsonObject.put("msg","权限分配成功");
        }
        return  jsonObject;
    }
}
