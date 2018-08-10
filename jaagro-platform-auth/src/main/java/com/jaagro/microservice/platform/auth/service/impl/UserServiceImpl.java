package com.jaagro.microservice.platform.auth.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.google.common.collect.Maps;
import com.jaagro.microservice.platform.api.service.auth.UserService;
import com.jaagro.microservice.platform.common.sms.AliyunSmsUtils;
import com.jaagro.microservice.platform.common.utils.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author tony
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 短信校验码模板code
     */
    private String smsTemplateCode = "SMS_141605531";
    /**
     * 创建验证码
     *
     * @return
     */
    @Override
    public Map<String, Object> createVerificationCode(String phoneNumber) {

        Random random = new Random();
        //获取5位随机验证码
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder verificationCodeBuilder = null;
        for(int i = 0; i < 5; i++){
            verificationCodeBuilder = stringBuilder.append(random.nextInt(9));
        }
        String verificationCode = verificationCodeBuilder.toString();
        Map<String, Object> map = Maps.newLinkedHashMap();
        map.put("code", verificationCode);
        try {
            AliyunSmsUtils.sendSms(phoneNumber, smsTemplateCode, map);
        } catch (ClientException e) {
            e.printStackTrace();
            return ServiceResult.error("短信发送失败");
        }
        redisTemplate.opsForValue().set(phoneNumber, verificationCode, 10, TimeUnit.MINUTES);
        return ServiceResult.toResult("验证码发送成功");
    }

    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public String getVerificationCode(String phoneNumber) {
        return redisTemplate.opsForValue().get(phoneNumber);
    }
}
