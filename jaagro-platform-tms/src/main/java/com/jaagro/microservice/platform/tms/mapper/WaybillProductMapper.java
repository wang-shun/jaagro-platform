package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.WaybillProduct;

public interface WaybillProductMapper {
    int deleteByPrimaryKey(Long waybillProductId);

    int insert(WaybillProduct record);

    int insertSelective(WaybillProduct record);

    WaybillProduct selectByPrimaryKey(Long waybillProductId);

    int updateByPrimaryKeySelective(WaybillProduct record);

    int updateByPrimaryKey(WaybillProduct record);
}