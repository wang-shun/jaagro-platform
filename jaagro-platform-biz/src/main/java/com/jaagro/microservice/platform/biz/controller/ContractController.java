package com.jaagro.microservice.platform.biz.controller;

import com.jaagro.microservice.platform.api.dto.crm.CreateContractDto;
import com.jaagro.microservice.platform.api.service.crm.ContractClientService;
import com.jaagro.microservice.platform.common.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractClientService contractClientService;

    @PostMapping("/createContract")
    public BaseResponse createContract(@RequestBody CreateContractDto dto){
        return BaseResponse.service(contractClientService.createContract(dto));
    }
}
