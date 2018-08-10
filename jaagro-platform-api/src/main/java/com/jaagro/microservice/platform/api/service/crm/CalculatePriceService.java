package com.jaagro.microservice.platform.api.service.crm;

import com.jaagro.microservice.platform.api.dto.crm.PriceCriteriaDto;

import java.util.Map;

/**
 * 价格计算service
 * @author tony
 */
public interface CalculatePriceService {

    /**
     * 根据条件计算费用
     * @param dto
     * @return
     */
    Map<String, Object> calculatePrice(PriceCriteriaDto dto);
}
