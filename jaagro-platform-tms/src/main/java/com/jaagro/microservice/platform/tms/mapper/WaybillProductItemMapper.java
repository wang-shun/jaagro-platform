package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.WaybillProductItem;

public interface WaybillProductItemMapper {
    int deleteByPrimaryKey(Long waybillProductItemId);

    int insert(WaybillProductItem record);

    int insertSelective(WaybillProductItem record);

    WaybillProductItem selectByPrimaryKey(Long waybillProductItemId);

    int updateByPrimaryKeySelective(WaybillProductItem record);

    int updateByPrimaryKey(WaybillProductItem record);
}