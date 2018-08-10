package com.jaagro.microservice.platform.biz;

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
@ComponentScan("com.jaagro.microservice.platform")
@EnableFeignClients(basePackages = {"com.jaagro.microservice.platform.api"})
@SpringBootApplication
public class JaagroPlatformBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaagroPlatformBizApplication.class, args);
    }
}
