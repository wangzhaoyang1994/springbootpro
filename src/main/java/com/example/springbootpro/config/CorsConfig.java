package com.example.springbootpro.config;

import com.thetransactioncompany.cors.CORSConfiguration;
import com.thetransactioncompany.cors.CORSConfigurationException;
import com.thetransactioncompany.cors.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 跨域的配置类
 */
@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean corsFilter() {
        Properties props = new Properties();
        props.setProperty("cors.allowGenericHttpRequests", "true");
        props.setProperty("cors.allowOrigin", "*");
        props.setProperty("cors.allowSubdomains", "true");
        props.setProperty("cors.supportedMethods", "GET, HEAD, POST, OPTIONS");
        props.setProperty("cors.supportedHeaders", "*");
        props.setProperty("cors.supportsCredentials", "true");
        props.setProperty("cors.maxAge", "3600");
        CORSConfiguration config = null;
        try {
            config = new CORSConfiguration(props);
        } catch (CORSConfigurationException e) {
            e.printStackTrace();
        }
        FilterRegistrationBean bean = new FilterRegistrationBean(new CORSFilter(config));
        bean.setOrder(0);
        return bean;
    }

}
