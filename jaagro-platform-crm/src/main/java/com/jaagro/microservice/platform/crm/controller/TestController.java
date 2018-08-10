package com.jaagro.microservice.platform.crm.controller;

import com.jaagro.microservice.platform.api.dto.auth.TokenInfo;
import com.jaagro.microservice.platform.api.service.auth.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 */
@RestController
public class TestController {

    @Autowired
    private CurrentUserService userService;

    @GetMapping("/test")
    public String test1(){
        return "success crm";
    }

    @GetMapping("/test2")
    public TokenInfo test2(){
        return userService.getCurrentUser();
    }
}
