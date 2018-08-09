package com.jaagro.microservice.platform.auth.controller;

import com.auth0.jwt.interfaces.Claim;
import com.jaagro.microservice.platform.auth.service.AuthService;
import com.jaagro.microservice.platform.api.service.auth.UserService;
import com.jaagro.microservice.platform.component.utils.BaseResponse;
import com.jaagro.microservice.platform.component.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author tony
 */
@RestController
public class TestController {

    @Autowired
    AuthService authService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public BaseResponse test(){
        Map<String, Object> map = null;
        try {
            map = authService.createTokenByPassword("tony", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResponse.service(map);
    }

    @GetMapping("/test2")
    public Map<String, Claim> test2(String token){

        Map<String, Claim> map = null;
        try {
            map = authService.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/test3")
    public void test3(String token){

        try {
            authService.getTokenInfo(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/test4")
    public String test4(){
        Random random = new Random();
        System.out.println(random.nextInt(9));
        redisTemplate.opsForValue().set("15000489584", "384773", 10, TimeUnit.SECONDS);
        return redisTemplate.opsForValue().get("15000489584");
    }

    @GetMapping("/test5")
    public String test5(String phone){
        Random random = new Random();
        StringBuilder salt = null;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 5; i++){
            salt = stringBuilder.append(random.nextInt(9));
        }
        return salt.toString();
    }

    @GetMapping("/test6")
    public Map<String, String> test6(String password){

        Map<String, String> map = PasswordEncoder.encodePassword(password);
        return map;
    }

    @GetMapping("/test7")
    public String test7(){
        System.out.println(redisTemplate.opsForValue().get("123"));
        return redisTemplate.opsForValue().get("123");
    }
}
