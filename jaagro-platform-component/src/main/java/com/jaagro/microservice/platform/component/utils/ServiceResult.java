package com.jaagro.microservice.platform.component.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * @author tobiasy
 */
public class ServiceResult {
    /**
     * 被使用到error()的方法中正确的返回结果
     * @param data
     * @return
     */
    public static Map<String, Object> toResult(Object data) {
        Map<String, Object> map = new HashMap();
        map.put(ServiceKey.success.name(), true);
        map.put(ServiceKey.data.name(), data);
        return map;
    }

    /**
     * 快捷通过属性指定service层将会出问题
     * @param attribute 指定出错的属性名
     * @param msg 返回调用者的原因
     * @return
     */
    public static Map<String, Object> error(String msg,String attribute) {
        Map<String, Object> map = new HashMap();
        map.put(ServiceKey.success.name(), false);
        map.put(ServiceKey.attribute.name(), attribute);
        map.put(ServiceKey.msg.name(), msg);
        return map;
    }
    public static Map<String, Object> error(int status, String msg, String attribute) {
        Map<String, Object> map = new HashMap();
        map.put(ServiceKey.success.name(), false);
        map.put(ServiceKey.attribute.name(), attribute);
        map.put(ServiceKey.msg.name(), msg);
        map.put(ServiceKey.status.name(), status);
        return map;
    }



    public static Map<String, Object> error(int status) {
        Map<String, Object> map = new HashMap();
        map.put(ServiceKey.success.name(), false);
        map.put(ServiceKey.status.name(), status);
        return map;
    }

    public static Map<String, Object> error(String msg) {
        Map<String, Object> map = new HashMap();
        map.put(ServiceKey.success.name(), false);
        map.put(ServiceKey.msg.name(), msg);
        return map;
    }

    public static Map<String, Object>
    error(int status, String msg) {
        Map<String, Object> map = new HashMap();
        map.put(ServiceKey.success.name(), false);
        map.put(ServiceKey.msg.name(), msg);
        map.put(ServiceKey.status.name(), status);
        return map;
    }

    public static void main(String[] args) {
        Map<String, Object> map = error(null, "customerId:1");
        Map<String, Object> map1 = error("查询出错");
        Map<String, Object> map2 = toResult(new Object());
        Map<String, Object> map3 = error(300,"服务器出错");
        Map<String, Object> map4 = error(400,"服务器出错","customerId:1");
        Map<String, Object> map5 = error(503);
        System.out.println(map);
        System.out.println(BaseResponse.service(map));
        System.out.println(BaseResponse.service(map1));
        System.out.println(BaseResponse.service(map2));
        System.out.println(BaseResponse.service(map3));
        System.out.println(BaseResponse.service(map4));
        System.out.println(BaseResponse.service(map5));
    }
}
