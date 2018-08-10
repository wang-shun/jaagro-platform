package com.jaagro.microservice.platform.tms.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tony
 */
@Data
@Accessors(chain = true)
public class WaybillCodeCounter implements Serializable {

    private Long waybillCodeCounterId;

    private Date waybillDate;

    private Integer waybillCounter;
}