package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.Waybill;

public interface WaybillMapper {

    int deleteByPrimaryKey(Long waybillId);

    int insert(Waybill record);

    int insertSelective(Waybill record);

    Waybill selectByPrimaryKey(Long waybillId);

    int updateByPrimaryKeySelective(Waybill record);

    int updateByPrimaryKey(Waybill record);
}