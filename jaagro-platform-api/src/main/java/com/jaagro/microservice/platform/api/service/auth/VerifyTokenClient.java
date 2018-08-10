package com.jaagro.microservice.platform.api.service.auth;

import com.jaagro.microservice.platform.api.dto.auth.TokenInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 入参必须要有注解
 * @author tony
 */
@FeignClient(value = "auth")
public interface VerifyTokenClient {

    /**
     * 验证token
     * 提供给gateway调用
     * @param token
     * @return
     */
    @PostMapping("/verifyToken")
    boolean verifyToken(@RequestParam("token") String token);

    /**
     * 获取token相关的用户信息
     * 提供给其他微服务使用
     * @param token
     * @return
     */
    @PostMapping("/getTokenInfo")
    TokenInfo getTokenInfo(@RequestParam("token") String token);
}
