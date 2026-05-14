
package com.bilibili.myblogbackend.config;

import com.bilibili.myblogbackend.util.oss.MinioProperties;
import io.minio.MinioClient;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Bean
    public MinioClient minioClient(MinioProperties minioProperties, OkHttpClient okHttpClient) {
        return MinioClient.builder().endpoint(minioProperties.getEndpoint()).credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey()).httpClient(okHttpClient).build();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().connectionPool(new ConnectionPool(100, 5L, TimeUnit.MINUTES)).build();
    }
}

