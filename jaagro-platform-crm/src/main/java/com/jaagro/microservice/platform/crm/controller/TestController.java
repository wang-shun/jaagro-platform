package com.jaagro.microservice.platform.crm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test1(){
        return "success crm";
    }
}
