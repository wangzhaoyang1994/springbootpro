package com.example.springbootpro.controller;

import com.example.springbootpro.entity.Menu;
import com.example.springbootpro.entity.TestRedis;
import com.example.springbootpro.service.MenuService;
import com.example.springbootpro.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {
    private static int ExpireTime = -1; //redis缓存不过期
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private MenuService menuService;
    @RequestMapping("/set")
    public boolean redisSet(String key) {
        TestRedis test = new TestRedis();
        test.setName("王昭洋");
        test.setValue("26岁");
        key="1478";
        List<Menu> list = menuService.findAllMenuByParentId(0);
        return redisUtil.set(key, list,ExpireTime);
    }

    @RequestMapping("get")
    @ResponseBody
    public Object redisGet(String key) {
        return redisUtil.get(key);
    }
}
