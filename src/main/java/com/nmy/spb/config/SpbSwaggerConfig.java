package com.nmy.spb.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author nmy
 * @title: SpbSwaggerConfig
 * @date 2022-03-19 17:44
 */
@Configuration
@EnableSwagger2
public class SpbSwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("nmy","aa","aa");
        return new ApiInfo(
                "SpringBooterveForSpb",
                "aa",
                "v1.0",
                "aa",
                contact,
                "Apache 2.0",
                "aa",
                new ArrayList<>());
    }
}
