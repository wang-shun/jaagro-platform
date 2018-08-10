package com.jaagro.microservice.platform.api.dto.crm.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author tony
 */
@Data
@Accessors(chain = true)
public class ContractSectionPriceReturnDto implements Serializable {

    private Integer selectionId;

    private Long contractPriceId;

    private Integer sectionType;

    private BigDecimal lowerLimit;

    private BigDecimal upperLimit;

    private BigDecimal price;

    private Integer selectionStatus;
}
