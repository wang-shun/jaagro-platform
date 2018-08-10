package com.jaagro.microservice.platform.auth.service;

import com.auth0.jwt.interfaces.Claim;
import com.jaagro.microservice.platform.api.dto.auth.TokenInfo;

import java.util.Map;

/**
 * @author tony
 */
public interface AuthService {
    /**
     * 获取token
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> createTokenByPassword(String username, String password);

    /**
     * 获取token
     * @param phoneNumber 手机号码
     * @param verificationCode 验证码
     * @return token
     */
    Map<String, Object> createTokenByPhone(String phoneNumber, String verificationCode);

    /**
     * 注销token
     * @param token token
     */
    void invalidate(String token);

    /**
     * 刷新token
     * @param token token
     * @return refreshToken
     */
    String refresh(String token);

    /**
     * 获取token详情
     * @param token
     * @return
     * @throws Exception
     */
    TokenInfo getTokenInfo(String token) throws Exception;


    /**
     * 验证token
     * @param token
     * @return
     * @throws Exception
     */
    Map<String, Claim> verifyToken(String token) throws Exception;
}
