package com.jaagro.microservice.platform.api.service.crm;

import com.jaagro.microservice.platform.api.dto.crm.CreateContractDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author tony
 */
@FeignClient(value = "crm")
public interface ContractClientService {

    /**
     * 创建合同
     * @param dto
     * @return
     */
    @PostMapping("/contract")
    Map<String, Object> createContract(@RequestBody CreateContractDto dto);
}
