package com.bilibili.myblogbackend;

import com.bilibili.myblogbackend.util.oss.MinioProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MinioProperties.class)
@MapperScan(basePackages = "com.bilibili.myblogbackend.mapper")
public class MyblogbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogbackendApplication.class, args);
    }

}
