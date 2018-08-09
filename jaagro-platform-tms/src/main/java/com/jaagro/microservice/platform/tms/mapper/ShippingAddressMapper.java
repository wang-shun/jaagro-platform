package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.ShippingAddress;

public interface ShippingAddressMapper {
    int deleteByPrimaryKey(Long shippingAddressId);

    int insert(ShippingAddress record);

    int insertSelective(ShippingAddress record);

    ShippingAddress selectByPrimaryKey(Long shippingAddressId);

    int updateByPrimaryKeySelective(ShippingAddress record);

    int updateByPrimaryKey(ShippingAddress record);

    ShippingAddress getShippingAddressByName(String name);
}