package com.jaagro.microservice.platform.biz.controller;

import com.jaagro.microservice.platform.api.dto.tms.CreateWaybillByExcelDto;
import com.jaagro.microservice.platform.api.service.tms.WaybillServiceClient;
import com.jaagro.microservice.platform.component.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tony
 */
@RestController
@RequestMapping("/waybill")
public class WaybillController {

    @Autowired
    private WaybillServiceClient serviceClient;

    @Autowired
    HttpServletRequest request;

    @PostMapping("/createWaybillByExcel")
    public BaseResponse createWaybillByExcel(@RequestBody CreateWaybillByExcelDto dto){
        return BaseResponse.service(serviceClient.createWaybillByExcel(dto));
    }
    @GetMapping("/test1")
    public String test1(){
        return request.getHeader("token");
    }
}
