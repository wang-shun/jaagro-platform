package com.jaagro.microservice.platform.component.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * @author admin
 */
public class BaseResponse implements Serializable {

    /**
     * 状态
     */
    private int statusCode = StatusCode.OPERATION_SUCCESS.getCode();

    /**
     * 错误信息 返回错误时必填
     */
    private String statusMsg = StatusCode.OPERATION_SUCCESS.getMsg();

    /**
     * 数据
     */
    private Object data;

    public String getStatusMsg() {
        return statusMsg;
    }

    public BaseResponse setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BaseResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public BaseResponse setStatusCode(StatusCode statusCode) {
        this.setStatusCode(statusCode.getCode());
        this.setStatusMsg(statusCode.getMsg());
        return this;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "statusCode=" + statusCode +
                ", statusMsg='" + statusMsg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * @param date
     * @return BaseResponse
     * @description 成功结果的的快速生成
     */
    public static BaseResponse successInstance(Object date) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(StatusCode.OPERATION_SUCCESS);
        return response.setData(date);
    }

    /**
     * @param msg
     * @return BaseResponse
     * @description 成功结果的的快速生成
     */
    public static BaseResponse successInstance(String msg) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(StatusCode.OPERATION_SUCCESS);
        response.setStatusMsg(msg);
        return response;
    }

    /**
     * @param errorMsg 错误信息
     * @return BaseResponse
     * @description 错误结果的的快速生成
     */
    public static BaseResponse errorInstance(String errorMsg) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(StatusCode.SERVER_ERROR);
        response.setStatusMsg(errorMsg);
        return response;
    }

    /**
     * 尚未登录
     * @return
     */
    public static BaseResponse notLogin() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(StatusCode.UNAUTHORIZED_ERROR);
        response.setStatusMsg("登录失效，请重新登录！");
        return response;
    }

    /**
     * 创建没有查询到数据结果返回对象
     *
     * @return BaseResponse
     */
    public static BaseResponse queryDataEmpty() {
        BaseResponse response = new BaseResponse();
        StatusCode statusCode = StatusCode.QUERY_DATA_EMPTY;
        response.setStatusCode(statusCode);
        return response;
    }

    /**
     * 创建没有id匹配信息返回对象
     *
     * @return BaseResponse
     */
    public static BaseResponse idError(String... idName) {
        BaseResponse response = new BaseResponse();
        StatusCode statusCode = StatusCode.ID_VALUE_ERROR;
        response.setStatusCode(statusCode);
        for (String id : idName) {
            response.setStatusMsg(statusCode.getMsg() + ":参数" + id);
        }
        return response;
    }

    /**
     * 快捷返回主键为null
     * @param idName
     * @return
     */
    public static BaseResponse idNull(String... idName) {
        BaseResponse response = new BaseResponse();
        StatusCode statusCode = StatusCode.ID_VALUE_NULL;
        response.setStatusCode(statusCode);
        for (String id : idName) {
            response.setStatusMsg(statusCode.getMsg() + ":没有传输参数" + id);
        }
        return response;
    }

    /**
     * 与service层中ServiceResult对象引用error()或者toResult()方法添加service层自定义错误匹配使用
     * 但为了规范，只能在Controller中使用
     * @param map
     * @return
     */
    public static BaseResponse service(Map<String, Object> map) {
        BaseResponse baseResponse = new BaseResponse();
        Object data = map.get(ServiceKey.data.name());
        Boolean success = (Boolean) map.get(ServiceKey.success.name());
        if (success) {
            return successInstance(data);
        }
        baseResponse.setData(data);
        Object status = map.get(ServiceKey.status.name());
        int statusCode;
        if (status == null) {
            statusCode = StatusCode.SERVER_ERROR.getCode();
        } else {
            statusCode = (int) status;
        }
        baseResponse.setStatusCode(statusCode);
        Object att = map.get(ServiceKey.attribute.name());
        Object msg = map.get(ServiceKey.msg.name());
        String message = null;
        if (msg == null) {
            if (att != null) {
                message = "(" + att + ")";
            }
        } else {
            message = (String) msg;
            if (att != null) {
                message += "(" + att + ")";
            }
        }
        baseResponse.setStatusMsg(message);
        return baseResponse;
    }

}
