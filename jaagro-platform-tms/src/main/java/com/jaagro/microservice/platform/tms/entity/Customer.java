package com.jaagro.microservice.platform.tms.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author tony
 */
@Data
public class Customer {

    private Long customerId;

    private String name;

    private Long customerTypeId;

    private Long branchId;

    private Long adminRegionId;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String contactPerson;

    private String contactNumber;

    private String contactMobile;

    private String fax;

    private Long invoiceTypeId;

    private String invoiceHeader;

    private String taxNumber;

    private Integer billingPeriod;

    private Long userId;

    private Date createdDate;

    private Long createdUserId;

    private Boolean isDeleted;

    private Boolean isActive;

    private Long version;
}