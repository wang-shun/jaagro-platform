package com.jaagro.microservice.platform.crm.mapper;

import com.jaagro.microservice.platform.crm.entity.ContractLog;

public interface ContractLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ContractLog record);

    int insertSelective(ContractLog record);

    ContractLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractLog record);

    int updateByPrimaryKey(ContractLog record);
}