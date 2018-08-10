package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.WaybillLog;

public interface WaybillLogMapper {

    int deleteByPrimaryKey(Long waybillLogId);

    int insert(WaybillLog record);

    int insertSelective(WaybillLog record);

    WaybillLog selectByPrimaryKey(Long waybillLogId);

    int updateByPrimaryKeySelective(WaybillLog record);

    int updateByPrimaryKey(WaybillLog record);
}