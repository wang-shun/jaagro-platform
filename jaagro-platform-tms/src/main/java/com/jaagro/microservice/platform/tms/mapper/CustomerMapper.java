package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.api.dto.tms.CustomerReturnDto;
import com.jaagro.microservice.platform.tms.entity.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    CustomerReturnDto getCustomerById(Long id);
}