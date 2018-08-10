package com.jaagro.microservice.platform.common.oss;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class KeyToUrlDto {
    @JsonIgnore
    private String packageName;
    private Integer size;
    private String[] key;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String[] getKey() {
        return key;
    }

    public void setKey(String[] key) {
        this.key = key;
    }
}
