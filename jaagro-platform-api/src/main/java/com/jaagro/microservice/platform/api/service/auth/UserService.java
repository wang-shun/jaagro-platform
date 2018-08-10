package com.jaagro.microservice.platform.api.service.auth;

import com.jaagro.microservice.platform.api.dto.auth.TokenInfo;

import java.util.Map;

/**
 * @author tony
 */
public interface UserService {

    /**
     * 创建验证码
     * @param phoneNumber
     * @return
     */
    Map<String, Object> createVerificationCode(String phoneNumber);

    /**
     * 获取验证码
     * @param phoneNumber
     * @return
     */
    String getVerificationCode(String phoneNumber);
}
