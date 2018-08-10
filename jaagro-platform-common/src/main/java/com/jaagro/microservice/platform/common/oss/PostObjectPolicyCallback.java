package com.jaagro.microservice.platform.common.oss;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class PostObjectPolicyCallback {
    private String callbackUrl;
    private String callbackBody;

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getCallbackBody() {
        return callbackBody;
    }

    public void setCallbackBody(String callbackBody) {
        this.callbackBody = callbackBody;
    }

    @Override
    public String toString(){
        String callback = "{\"callbackUrl\":\""+callbackUrl+"\",\"callbackBody\":\""+callbackBody+"\",\"callbackBodyType\":\"application/json\"}";
        byte[] bytes = new byte[0];
        try {
            bytes = Base64.encodeBase64(callback.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

}