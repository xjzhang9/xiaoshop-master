package com.xjzhang.generator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xjzhang
 */
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
public class GeneratorApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeneratorApplication.class, args);
	}
}
