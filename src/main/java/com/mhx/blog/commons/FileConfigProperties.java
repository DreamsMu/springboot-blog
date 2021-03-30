package com.mhx.blog.commons;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file")
public class FileConfigProperties {
    private String locationPath;
    private String articleImgPath;
    private String tagsImgPath;
    private String adminImgPath;
}
