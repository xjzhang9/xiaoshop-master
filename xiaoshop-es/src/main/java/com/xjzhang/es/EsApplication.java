package com.xjzhang.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/14 20:17
 */
@EnableFeignClients
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
public class EsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }
}
