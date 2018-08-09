package com.jaagro.microservice.platform.tms.entity;

import lombok.Data;

/**
 * @author tony
 */
@Data
public class ProductType {

    private Long productTypeId;

    private String name;

    private Boolean isDeleted;

    private Integer code;
}