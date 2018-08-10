package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.WaybillValidatingStatus;

public interface WaybillValidatingStatusMapper {
    int deleteByPrimaryKey(Long waybillValidatingStatusId);

    int insert(WaybillValidatingStatus record);

    int insertSelective(WaybillValidatingStatus record);

    WaybillValidatingStatus selectByPrimaryKey(Long waybillValidatingStatusId);

    int updateByPrimaryKeySelective(WaybillValidatingStatus record);

    int updateByPrimaryKey(WaybillValidatingStatus record);
}