package com.jaagro.microservice.platform.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tony
 */
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
@ComponentScan("com.jaagro.microservice.platform")
@EnableFeignClients(basePackages = {"com.jaagro.microservice.platform.api"})
public class JaagroPlatformGatewayApplication {
    public static void main(String[] args) {

        SpringApplication.run(JaagroPlatformGatewayApplication.class, args);
    }
}
