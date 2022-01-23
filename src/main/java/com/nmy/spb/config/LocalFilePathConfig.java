package com.nmy.spb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author nmy
 * @title: LocalFilePathConfig
 * @date 2022-01-23 20:35
 */
@Configuration
public class LocalFilePathConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/php/php/PHPTutorial/WWW/spb");
    }
}
