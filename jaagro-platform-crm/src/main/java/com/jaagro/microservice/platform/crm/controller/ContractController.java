package com.jaagro.microservice.platform.crm.controller;

import com.jaagro.microservice.platform.api.dto.crm.CreateContractDto;
import com.jaagro.microservice.platform.api.service.crm.ContractService;
import com.jaagro.microservice.platform.component.utils.ServiceResult;
import com.jaagro.microservice.platform.component.utils.StatusCode;
import com.jaagro.microservice.platform.crm.mapper.ContractMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liqiangping
 */
@RestController
@Api(value = "contract", description = "客户管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContractController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractMapper contractMapper;

    @ApiOperation("合同新增")
    @PostMapping("/contract")
    public Map<String, Object> createContract(@RequestBody CreateContractDto dto) {

        if (StringUtils.isEmpty(dto.getCustomerId())) {
            return ServiceResult.error(StatusCode.QUERY_DATA_ERROR.getCode(), "合同客户不能为空");
        }
        Map<String, Object> result = null;
        try {
            result = contractService.createContract(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.error(StatusCode.QUERY_DATA_ERROR.getCode(), e.getMessage());
        }
        return result;
    }

    @ApiOperation("合同修改")
    @PutMapping("/contract")
    public Map<String, Object> updateContract(@RequestBody CreateContractDto dto) {

        if (contractMapper.selectByPrimaryKey(dto.getId()) == null) {
            return ServiceResult.error(StatusCode.ID_VALUE_NULL.getCode(), dto.getId() + " :合同不存在");
        }
        if (StringUtils.isEmpty(dto.getCustomerId())) {
            return ServiceResult.error(StatusCode.ID_VALUE_ERROR.getCode(), "合同客户不能为空");
        }
        Map<String, Object> result = null;
        try {
            result = contractService.updateContract(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.error(StatusCode.QUERY_DATA_ERROR.getCode(), e.getMessage());
        }
        return result;
    }

}
