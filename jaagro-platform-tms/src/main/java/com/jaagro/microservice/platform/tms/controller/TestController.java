package com.jaagro.microservice.platform.tms.controller;

import com.jaagro.microservice.platform.api.dto.tms.LoadExcelDto;
import com.jaagro.microservice.platform.api.service.auth.VerifyTokenClient;
import com.jaagro.microservice.platform.tms.entity.Branch;
import com.jaagro.microservice.platform.tms.entity.User;
import com.jaagro.microservice.platform.tms.service.CodeGenerationService;
import com.jaagro.microservice.platform.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    VerifyTokenClient tokenClient;

    @Autowired
    private CodeGenerationService codeGenerationService;

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("/test2")
    public boolean test2(){
        LoadExcelDto dto = new LoadExcelDto();
        return StringUtils.isEmpty(dto);
    }

    @GetMapping("/test3")
    public User test3(){
        return userService.getCurrentUser();
    }
    @GetMapping("/test4")
    public Branch test4(){
        return userService.getCurrentBranch();
    }

    @GetMapping("/test5")
    public void test5(String token){
        System.out.println(tokenClient.getTokenInfo(token));
    }
}
