package com.jaagro.microservice.platform.api.service.auth;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author tony
 */
@FeignClient(value = "auth")
public interface UserClientService {

    /**
     * 获取验证码
     * @param phoneNumber
     * @return
     */
    @PostMapping("/createVerificationCode")
    Map<String, Object> createVerificationCode(@RequestParam("phoneNumber") String phoneNumber);
}
