package com.example.springbootpro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 图片保存路径，自动从yml文件中获取数据
     *   示例： d:/images/
     */
    @Value("${imgPath}")
    private String fileSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 配置资源映射
         * 意思是：如果访问的资源路径是以“/image/”开头的，
         * 就给我映射到本机的“d:/image/”这个文件夹内，去找你要的资源
         * 注意：d:/image/ 后面的 “/”一定要带上
         */
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:"+fileSavePath);
    }
}

