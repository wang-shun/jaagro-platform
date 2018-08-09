package com.jaagro.microservice.platform.auth.controller;

import com.auth0.jwt.interfaces.Claim;
import com.jaagro.microservice.platform.api.dto.auth.TokenInfo;
import com.jaagro.microservice.platform.auth.entity.User;
import com.jaagro.microservice.platform.auth.mapper.UserMapper;
import com.jaagro.microservice.platform.auth.service.AuthService;
import com.jaagro.microservice.platform.component.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author tony
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 通过用户名密码获取token
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/token")
    public BaseResponse getTokenByPassword(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
        if(userMapper.getUerByUsername(username) == null){
            return BaseResponse.errorInstance(username + " :当前用户不存在");
        }
        Map<String, Object> map = authService.createTokenByPassword(username, password);
        return BaseResponse.service(map);
    }

    /**
     * 通过短信验证码获取token
     * @param phoneNumber
     * @param verificationCode
     * @return
     */
    @GetMapping("/token")
    public BaseResponse getTokenByPhone(@RequestParam("phoneNumber") String phoneNumber,
                                        @RequestParam("verificationCode") String verificationCode){
        User user = userMapper.getUserByPhone(phoneNumber);
        if(user == null){
            return BaseResponse.errorInstance(phoneNumber + " :未注册");
        }
        if(!verificationCode.equals(redisTemplate.opsForValue().get(phoneNumber))){
            return BaseResponse.errorInstance("验证码不正确");
        }

        Map<String, Object> map = authService.createTokenByPhone(phoneNumber, verificationCode);
        return BaseResponse.service(map);
    }

    /**
     * 提供给gateway层的zuulFilter做验证使用，所以不使用baseResponse对象封装
     * @param token
     * @return
     */
    @PostMapping("/verifyToken")
    public boolean verifyToken(String token){
        try {
            authService.verifyToken(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PostMapping("/decodeToken")
    public Map<String, Claim> decodeToken(String token){
        Map<String, Claim> map = null;
        try {
            map = authService.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 提供给其他微服务调用，用于获取当前token对应的user
     * @param token
     * @return
     */
    @PostMapping("/getTokenInfo")
    public TokenInfo getTokenInfo(String token){
        TokenInfo tokenInfo = null;
        try {
            tokenInfo = authService.getTokenInfo(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenInfo;
    }
}
