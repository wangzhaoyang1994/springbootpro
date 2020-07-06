package com.example.springbootpro.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

public class sessionUtils {

    //从session获取数据
    public  static  Object get(HttpServletRequest request,String key){
        return request.getSession().getAttribute(key);
    }
    //在session中存数据
    public static void set(HttpServletRequest request,String key,Object val){
        request.getSession().setAttribute(key,val);
    }
    //从session中删除数据
    public static void remove(HttpServletRequest request,String key){
        request.getSession().removeAttribute(key);
    }
}
