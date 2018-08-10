package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.WaybillStatus;

public interface WaybillStatusMapper {
    int deleteByPrimaryKey(Long waybillStatusId);

    int insert(WaybillStatus record);

    int insertSelective(WaybillStatus record);

    WaybillStatus selectByPrimaryKey(Long waybillStatusId);

    int updateByPrimaryKeySelective(WaybillStatus record);

    int updateByPrimaryKey(WaybillStatus record);
}