package com.mhx.blog.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@Configuration
public class MultipartConfig {

    @Autowired
    private FileConfigProperties fileConfigProperties;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        String locationPath = fileConfigProperties.getLocationPath();
        String articleImgPath = fileConfigProperties.getArticleImgPath();
        String tagsImgPath = fileConfigProperties.getTagsImgPath();
        String adminImgPath = fileConfigProperties.getAdminImgPath();
        File tmpFile = new File(locationPath + "/" + articleImgPath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        File tagsDir = new File(locationPath + "/" + tagsImgPath);
        if (!tagsDir.exists()) {
            tagsDir.mkdirs();
        }
        File adminDir = new File(locationPath + "/" + adminImgPath);
        if (!adminDir.exists()) {
            adminDir.mkdirs();
        }
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(locationPath);
        return factory.createMultipartConfig();
    }
}
