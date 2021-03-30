package com.mhx.blog.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    private FileConfigProperties fileConfigProperties;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:static/");
        registry.addResourceHandler("/" + fileConfigProperties.getArticleImgPath() + "/**")
                .addResourceLocations("file:" + fileConfigProperties.getLocationPath() + "/" + fileConfigProperties.getArticleImgPath() + "/");
        registry.addResourceHandler("/" + fileConfigProperties.getTagsImgPath() + "/**")
                .addResourceLocations("file:" + fileConfigProperties.getLocationPath() + "/" + fileConfigProperties.getTagsImgPath() + "/");
        registry.addResourceHandler("/" + fileConfigProperties.getAdminImgPath() + "/**")
                .addResourceLocations("file:" + fileConfigProperties.getLocationPath() + "/" + fileConfigProperties.getAdminImgPath() + "/");
    }
}
