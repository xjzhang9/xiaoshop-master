package com.xjzhang.xiaoshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2022/1/2 18:33
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Cart1Application {
    public static void main(String[] args) {
        SpringApplication.run(Cart1Application.class, args);
    }
}
