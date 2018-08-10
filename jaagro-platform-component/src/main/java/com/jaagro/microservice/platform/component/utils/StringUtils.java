package com.jaagro.microservice.platform.component.utils;

import java.util.Random;

/**
 * String对象的常用方法封装
 *
 * @author tobiasy
 */
public class StringUtils {
    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String capitalizeCase(String str) {
        char[] ch = str.toCharArray();
        char a = 'a';
        char z = 'z';
        if (ch[0] >= a && ch[0] <= z) {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 根据位数 生成随机数
     *
     * @param number 随机数位数
     * @return
     */
    public String createRandom(int number) {
        int l = 1;
        for (int i = 0; i < number; i++) {
            l = 10 * l;
        }
        int max = l - 1;
        int min = l / 10;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);
    }
}
