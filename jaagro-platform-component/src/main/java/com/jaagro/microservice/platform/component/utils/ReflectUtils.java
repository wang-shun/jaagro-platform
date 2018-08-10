package com.jaagro.microservice.platform.component.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tobiasy
 */
public class ReflectUtils {
    /**
     * <p>获取clazz中的所有属性及其父类所有属性</p>
     *
     * @param clazz
     * @return
     */
    public static List<Field> getExtendsField(Class<? extends Object> clazz) {
        Field[] superField = clazz.getSuperclass().getDeclaredFields();
        Field[] targetField = clazz.getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        for (Field field : superField) {
            fieldList.add(field);
        }
        for (Field field : targetField) {
            Boolean f = true;
            for (Field field1 : superField) {
                if (field1.getName().equals(field.getName())) {
                    f = false;
                }
            }
            if (f) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }
}
