package com.jaagro.microservice.platform.biz.controller;

import com.jaagro.microservice.platform.api.service.auth.UserClientService;
import com.jaagro.microservice.platform.component.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 */
@RestController
@RequestMapping("/uaa")
public class UserController {

    @Autowired
    private UserClientService userClientService;

    @PostMapping("/createVerificationCode")
    public BaseResponse createVerificationCode(@RequestParam("phoneNumber") String phoneNumber){
        return BaseResponse.service(userClientService.createVerificationCode(phoneNumber));
    }
}
