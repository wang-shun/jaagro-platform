package com.jaagro.microservice.platform.api.service.crm;

import com.jaagro.microservice.platform.api.dto.crm.CreateContractDto;

import java.util.Map;

/**
 * @author tony
 */
public interface ContractService {

    /**
     * 创建合同
     * @param dto
     * @return
     */

    Map<String, Object> createContract(CreateContractDto dto);

    /**
     * 修改合同
     * @param dto
     * @return
     */
    Map<String, Object> updateContract(CreateContractDto dto);
}
