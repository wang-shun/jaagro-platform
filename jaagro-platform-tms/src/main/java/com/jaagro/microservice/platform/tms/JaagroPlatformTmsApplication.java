package com.jaagro.microservice.platform.tms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tony
 */
@EnableEurekaClient
@EnableCircuitBreaker
@MapperScan("com.jaagro.microservice.platform.tms.mapper")
@SpringBootApplication
@ComponentScan("com.jaagro.microservice.platform")
@EnableFeignClients(basePackages = {"com.jaagro.microservice.platform.api"})
public class JaagroPlatformTmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JaagroPlatformTmsApplication.class, args);
    }
}
