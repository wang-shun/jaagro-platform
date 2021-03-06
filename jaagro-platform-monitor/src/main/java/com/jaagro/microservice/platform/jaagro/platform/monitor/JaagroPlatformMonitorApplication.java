package com.jaagro.microservice.platform.jaagro.platform.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import zipkin.server.EnableZipkinServer;

/**
 * @author tony
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableTurbine
@EnableZipkinServer
public class JaagroPlatformMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaagroPlatformMonitorApplication.class, args);
    }
}
