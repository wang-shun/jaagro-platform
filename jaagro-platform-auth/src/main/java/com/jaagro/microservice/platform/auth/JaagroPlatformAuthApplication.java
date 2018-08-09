package com.jaagro.microservice.platform.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author tony
 */
@MapperScan("com.jaagro.microservice.platform.auth.mapper")
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class JaagroPlatformAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaagroPlatformAuthApplication.class, args);
    }
}
