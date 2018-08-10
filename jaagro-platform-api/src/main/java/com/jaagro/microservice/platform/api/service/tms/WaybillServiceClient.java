package com.jaagro.microservice.platform.api.service.tms;

import com.jaagro.microservice.platform.api.dto.tms.CreateWaybillByExcelDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 用于feign调用
 * @author tony
 */
@FeignClient(value = "tms")
public interface WaybillServiceClient {
    /**
     * excel 运单导入
     * @param dto
     * @return
     */
    @PostMapping("/waybill/createWaybillByExcel")
    Map<String, Object> createWaybillByExcel(@RequestBody CreateWaybillByExcelDto dto);
}
