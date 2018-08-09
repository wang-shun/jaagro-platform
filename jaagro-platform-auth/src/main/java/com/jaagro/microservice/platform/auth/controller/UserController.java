package com.jaagro.microservice.platform.auth.controller;

import com.jaagro.microservice.platform.api.service.auth.UserService;
import com.jaagro.microservice.platform.component.utils.ServiceResult;
import com.jaagro.microservice.platform.component.utils.VerificationPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author tony
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createVerificationCode")
    public Map<String, Object> createVerificationCode(@RequestParam("phoneNumber") String phoneNumber){
        if(VerificationPhone.isMobile(phoneNumber)){
            return userService.createVerificationCode(phoneNumber);
        }
        return ServiceResult.error("手机号码不正确，请重新输入");
    }
}
