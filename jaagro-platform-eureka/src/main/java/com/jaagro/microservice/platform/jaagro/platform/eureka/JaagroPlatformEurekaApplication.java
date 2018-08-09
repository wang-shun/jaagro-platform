package com.jaagro.microservice.platform.jaagro.platform.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author tony
 */
@EnableEurekaServer
@SpringBootApplication
public class JaagroPlatformEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JaagroPlatformEurekaApplication.class, args);
    }
}
