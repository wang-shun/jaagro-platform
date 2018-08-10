package com.jaagro.microservice.platform.component.utils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.jaagro.microservice.platform.component.utils.ReflectUtils.getExtendsField;


/**
 * 根据两个实体对象判断字段更改情况
 *
 * @author tobiasy
 */
public class BeanDifferentUtils {

    public static DifferentResult compare(Object origin, Object current) {
        Long start = System.currentTimeMillis();
        DifferentResult result = new DifferentResult();
        List<String> changedAttributes = new ArrayList<>();
        Map<String, Object> originValue = new LinkedHashMap<>();
        Map<String, Object> changedValue = new LinkedHashMap();
        Class<? extends Object> originClass = origin.getClass();
        Class<? extends Object> currentClass = current.getClass();
        List<Field> originField = getExtendsField(originClass);
        List<Field> targetField = getExtendsField(currentClass);
        for (Field field : originField) {
            String fieldName = field.getName();
            for (Field field1 : targetField) {
                String fieldName1 = field1.getName();
                Boolean f = field.getType().getName().equals(field1.getType().getName());
                if (fieldName.equals(fieldName1) && f) {
                    String methodName = "get" + StringUtils.capitalizeCase(fieldName);
                    try {
                        Object s1 = invoke(origin, methodName);
                        Object s2 = invoke(current, methodName);
                        Boolean flag = (s1 != null && s2 != null);
                        Boolean flag2 = !(s1 == null && s2 == null);
                        Boolean flagMark = false;
                        if (flag) {
                            if (!s1.equals(s2)) {
                                flagMark = true;
                            }
                        } else if (flag2) {
                            flagMark = true;
                        } else {
                            continue;
                        }
                        if (flagMark) {
                            changedAttributes.add(fieldName);
                            originValue.put(fieldName, s1);
                            changedValue.put(fieldName, s2);
                        }
                    } catch (NoSuchMethodException e) {
                        continue;
                    }

                }
            }
        }

        Long end = System.currentTimeMillis();
        result.setSpendTime(end - start);
        result.setChangedAttributes(changedAttributes);
        result.setChangedValue(changedValue);
        result.setOriginValue(originValue);
        return result;
    }

    public static Object invoke(Object obj, String methodName) throws NoSuchMethodException {
        Object value = null;
        Method getMethod = obj.getClass().getMethod(methodName, new Class[]{});
        if (getMethod == null) {
            return null;
        }
        try {
            value = getMethod.invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }


}

