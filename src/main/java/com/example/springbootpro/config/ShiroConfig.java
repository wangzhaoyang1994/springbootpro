package com.example.springbootpro.config;

import com.example.springbootpro.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 * shiro主要是使用内置的过滤器实现页面拦截
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean //把ShiroFilterFactoryBean放入spring环境中
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new  ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro的内置过滤器
        /**
         * shiro的内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         *        anon：无需登录也可以访问
         *        authc:必须认证才可以
         *        user:如果使用了remmeberMe的功能可以直接访问
         *        perms:该资源必须授予资源权限才可以访问
         *        role:该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap =new LinkedHashMap<>();//linkedHashMap是为了让存入Map中的数据有顺序
        filterMap.put("/add","authc");//左边是需要拦截的资源地址，右边是拦截方式
        filterMap.put("/update","authc");
        //修改跳转的登陆页面(如果拦截成功，那么默认跳转的页面是tologin.jsp)
        shiroFilterFactoryBean.setLoginUrl("/toLogin");//(前后端不分离的情况下，toLogin对应的是controller里面的方法)
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    //利用@Qualifier注解，找到@Bean注解里面的UserRealm
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")  //让这个方法返回的对象放入spring环境
    public UserRealm getRealm() {
        return new UserRealm();
    }
}
