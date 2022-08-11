package com.nmy.spb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.nmy.spb.mapper")
@EnableSwagger2
@SpringBootApplication
public class SpbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbApplication.class, args);
    }

}
