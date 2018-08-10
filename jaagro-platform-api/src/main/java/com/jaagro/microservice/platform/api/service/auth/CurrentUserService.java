package com.jaagro.microservice.platform.api.service.auth;

import com.jaagro.microservice.platform.api.dto.auth.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于获取当先token对应的user对象，封装在api层是为了让每个微服务可以直接调用
 * @author tony
 */
@Service
public class CurrentUserService {
    @Autowired
    private VerifyTokenClient tokenClient;
    @Autowired
    private HttpServletRequest request;

    public TokenInfo getCurrentUser(){
        String token = request.getHeader("token");
        return tokenClient.getTokenInfo(token);
    }
}
