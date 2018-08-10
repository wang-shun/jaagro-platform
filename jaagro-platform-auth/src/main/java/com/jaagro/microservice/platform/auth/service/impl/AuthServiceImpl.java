package com.jaagro.microservice.platform.auth.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jaagro.microservice.platform.api.dto.auth.TokenInfo;
import com.jaagro.microservice.platform.auth.entity.User;
import com.jaagro.microservice.platform.auth.mapper.UserMapper;
import com.jaagro.microservice.platform.auth.service.AuthService;
import com.jaagro.microservice.platform.component.exception.AuthorizationException;
import com.jaagro.microservice.platform.component.utils.MD5Utils;
import com.jaagro.microservice.platform.component.utils.ResponseStatusCode;
import com.jaagro.microservice.platform.component.utils.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供给feign调用，主要用于gateway层获取和验证token
 * @author tony
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    /**
     * 秘钥
     */
    private static String SECRET_KET;

    @Value("${jwt.secret.key}")
    public void setSecretKet(String secretKet) {
        SECRET_KET = secretKet;
    }

    @Override
    public Map<String, Object> createTokenByPassword(String username, String password) {

        //判断user是否有效
        User user = userMapper.getUerByUsername(username);
        String encodePassword = MD5Utils.encode(password, user.getSalt());
        if(!encodePassword.equals(user.getPassword())){
            return ServiceResult.error(ResponseStatusCode.UNAUTHORIZED_ERROR.getCode(), "用户名或密码错误");
        }
        return createToken(user);
    }

    @Override
    public Map<String, Object> createTokenByPhone(String phoneNumber, String verificationCode) {
        User user = userMapper.getUserByPhone(phoneNumber);
        return createToken(user);
    }

    private Map<String, Object> createToken(User user){
        //签发时间
        Date iatDate = new Date();

        //token过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 60 * 12);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>(16);
        map.put("alg", "HS256");
        map.put("type", "JWT");

        String token = null;
        try {
            token = JWT.create()
                    //header
                    .withHeader(map)
                    //payload：用于存放有效信息的地方
                    .withClaim("user", user.getId().toString())
                    //设置过期时间，过期时间必须大于签发时间
                    .withExpiresAt(expiresDate)
                    //设置签发时间
                    .withIssuedAt(iatDate)
                    //加密
                    .sign(Algorithm.HMAC256(SECRET_KET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ServiceResult.error("令牌生成失败");
        }
        return ServiceResult.toResult(token);
    }

    @Override
    public void invalidate(String token) {

    }

    @Override
    public String refresh(String token) {
        return null;
    }

    @Override
    public TokenInfo getTokenInfo(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            return new TokenInfo().setId(-9999L).setUsername("令牌无效或已过期");
        }
        String userId = jwt.getClaim("user").asString();
        //用于兼容老系统的token,后期重构完成后可删除
        String username = jwt.getClaim("user_name").asString();
        //需要查出user对象封装并返回
        TokenInfo tokenInfo = new TokenInfo();
        Long userIdL = null;
        User user = null;
        //目前框架的逻辑，通过user来判断token是否有效
        if(!StringUtils.isEmpty(userId)){
            userIdL = Long.valueOf(userId);
            user = userMapper.selectByPrimaryKey(userIdL);
            BeanUtils.copyProperties(user, tokenInfo);
        }
        //兼容老系统代码，后期重构完成后可删除
        if(!StringUtils.isEmpty(username)){
            tokenInfo.setUsername(username);
        }
        return tokenInfo;
    }

    @Override
    public Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            throw new AuthorizationException("令牌无效或已过期");
        }
        return jwt.getClaims();
    }

}
