package com.jaagro.microservice.platform.api.service.tms;

import com.jaagro.microservice.platform.api.dto.tms.CreateWaybillByExcelDto;

import java.util.Map;

/**
 * @author tony
 */
public interface WaybillService {

    /**
     * excel导入生成运单
     * @param waybills
     * @return
     * @throws Exception
     */
    Map<String, Object> createWaybillByExcel(CreateWaybillByExcelDto waybills) throws Exception;
}
