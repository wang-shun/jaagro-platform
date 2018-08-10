package com.jaagro.microservice.platform.crm.mapper;

import com.jaagro.microservice.platform.crm.entity.ContractSectionPrice;
import com.jaagro.microservice.platform.crm.entity.response.ContractSectionPriceReturnDto;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ContractSectionPriceMapper {

    int deleteByPrimaryKey(Integer selectionId);

    int insert(ContractSectionPrice record);

    int insertSelective(ContractSectionPrice record);

    ContractSectionPrice selectByPrimaryKey(Integer selectionId);

    int updateByPrimaryKeySelective(ContractSectionPrice record);

    int updateByPrimaryKey(ContractSectionPrice record);

    int deleteByPriceId(Long id);

    /**
     * 根据priceId获取所有sectionPrice记录
     *
     * @param priceId
     * @return
     */
    List<ContractSectionPriceReturnDto> listByPriceId(Long priceId);

    /**
     * 根据传入值查询出符合limit条件的sectionPrice
     *
     * @param contractPriceId
     * @param value
     * @return
     */
    ContractSectionPrice getByLimit(@Param("contractPriceId") Long contractPriceId, @Param("value") BigDecimal value);
}