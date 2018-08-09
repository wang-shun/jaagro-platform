package com.jaagro.microservice.platform.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tony
 */
@EnableEurekaClient
@EnableSwagger2
@ComponentScan("com.jaagro.microservice.platform")
@EnableFeignClients(basePackages = {"com.jaagro.microservice.platform.api"})
@SpringBootApplication
@MapperScan("com.jaagro.microservice.platform.crm.mapper")
public class JaagroPlatformCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaagroPlatformCrmApplication.class, args);
    }
}
