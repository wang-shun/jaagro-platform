package com.jaagro.microservice.platform.component.utils;


/**
 * @author admin
 * 枚举 -- 返回给客户端的状态码和状态信息
 */
public enum StatusCode {

    /**
     * 操作成功
     */
    OPERATION_SUCCESS(200, "操作成功"),

    /**
     * 参数错误
     */
    QUERY_DATA_ERROR(400, "参数错误"),

    /**
     * 未登录
     */
    UNAUTHORIZED_ERROR(401, "未登录"),

    /**
     * 权限不足
     */
    FORBIDDEN_ERROR(403, "权限不足"),

    /**
     * 服务器错误
     */
    SERVER_ERROR(500, "服务器错误"),

    /**
     * 参数id传输出错
     */
    ID_VALUE_NULL(502, "主键值空指针异常"),

    /**
     * 参数id空指针异常
     */
    ID_VALUE_ERROR(503, "主键值传输异常"),

    /**
     * 查询到数据为空
     */
    QUERY_DATA_EMPTY(504, "没有查询到数据");

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回状态信息
     */
    private String msg;

    StatusCode(int statusCode, String statusMsg) {
        this.code = statusCode;
        this.msg = statusMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
    
}
