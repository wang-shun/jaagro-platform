package com.jaagro.microservice.platform.tms.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tony
 */
@Data
public class PickUpAddress {

    private Long pickUpAddressId;

    private Long customerId;

    private String addressCode;

    private String addressName;

    private String contactPerson;

    private String contactNumber;

    private Long adminRegionId;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String notes;

    private Date createdDate;

    private Long createdUserId;

    private Boolean isDeleted;

    private Boolean isActive;

    private Boolean isDefault;

    private Long version;
}