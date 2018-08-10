package com.jaagro.microservice.platform.api.service.auth;

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
