package com.jaagro.microservice.platform.crm.mapper;

import com.jaagro.microservice.platform.api.dto.crm.CreateContractDto;
import com.jaagro.microservice.platform.api.dto.crm.request.ContractCriteriaDto;
import com.jaagro.microservice.platform.crm.entity.Contract;
import com.jaagro.microservice.platform.crm.entity.ContractPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ContractMapper {

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(Contract record);

    /**
     * 动态新增
     *
     * @param record
     * @return
     */
    int insertSelective(Contract record);

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    Contract selectByPrimaryKey(Long id);

    /**
     * 动态修改
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Contract record);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Contract record);

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    List<Contract> listByPage(ContractCriteriaDto dto);

}