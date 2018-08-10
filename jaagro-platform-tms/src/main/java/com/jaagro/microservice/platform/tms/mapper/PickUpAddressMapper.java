package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.api.dto.tms.PickUpAddressReturnDto;
import com.jaagro.microservice.platform.tms.entity.PickUpAddress;

public interface PickUpAddressMapper {

    int deleteByPrimaryKey(Long pickUpAddressId);

    int insert(PickUpAddress record);

    int insertSelective(PickUpAddress record);

    PickUpAddress selectByPrimaryKey(Long pickUpAddressId);

    int updateByPrimaryKeySelective(PickUpAddress record);

    int updateByPrimaryKey(PickUpAddress record);

    PickUpAddress getPickUpAddressByName(String addressName);
}