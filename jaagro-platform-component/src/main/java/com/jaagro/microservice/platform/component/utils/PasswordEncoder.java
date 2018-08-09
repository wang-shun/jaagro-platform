package com.jaagro.microservice.platform.component.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author tony
 */
public class PasswordEncoder {

    public static Map<String, String> encodePassword(String password) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder saltBuilder = null;
        for(int i = 0; i < 5; i++){
            saltBuilder = stringBuilder.append(random.nextInt(9));
        }
        String salt = saltBuilder.toString();
        String md5Password = MD5Utils.encode(password, salt);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("salt", salt);
        map.put("password", md5Password);
        return map;
    }
}
