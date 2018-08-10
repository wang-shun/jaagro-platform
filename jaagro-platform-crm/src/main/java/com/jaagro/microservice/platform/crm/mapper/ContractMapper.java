package com.jaagro.microservice.platform.crm.mapper;

import com.jaagro.microservice.platform.api.dto.crm.request.ContractCriteriaDto;
import com.jaagro.microservice.platform.crm.entity.Contract;
import com.jaagro.microservice.platform.crm.entity.response.ContractReturnDto;

import java.util.List;

public interface ContractMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    /**
     * 查询单个Dto
     *
     * @param id
     * @return
     */
    ContractReturnDto getByPrimaryKey(Long id);

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    List<ContractReturnDto> listByPage(ContractCriteriaDto dto);
}