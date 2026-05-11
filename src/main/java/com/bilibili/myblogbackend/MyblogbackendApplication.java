/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.MyblogbackendApplication
 *  com.bilibili.myblogbackend.util.oss.MinioProperties
 *  org.mybatis.spring.annotation.MapperScan
 *  org.springframework.boot.SpringApplication
 *  org.springframework.boot.autoconfigure.SpringBootApplication
 *  org.springframework.boot.context.properties.EnableConfigurationProperties
 */
package com.bilibili.myblogbackend;

import com.bilibili.myblogbackend.util.oss.MinioProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value={MinioProperties.class})
@MapperScan(basePackages={"com.bilibili.myblogbackend.mapper"})

public class MyblogbackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyblogbackendApplication.class, (String[])args);
    }



}

