package com.jaagro.microservice.platform.jaagro.platform.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author tony
 */
@EnableConfigServer
@SpringBootApplication
public class JaagroPlatformConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaagroPlatformConfigApplication.class, args);
    }
}
