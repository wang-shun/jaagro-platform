package com.jaagro.microservice.platform.api.dto.tms;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tony
 */
@Data
public class CreateWaybillByExcelDto implements Serializable {

    private List<LoadExcelDto> waybills;
}
