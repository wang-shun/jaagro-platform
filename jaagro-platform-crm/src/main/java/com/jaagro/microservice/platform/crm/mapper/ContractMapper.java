package com.jaagro.microservice.platform.crm.mapper;

import com.jaagro.microservice.platform.crm.entity.Contract;

public interface ContractMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}