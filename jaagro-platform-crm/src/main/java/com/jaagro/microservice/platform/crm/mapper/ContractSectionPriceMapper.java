package com.jaagro.microservice.platform.crm.mapper;

import com.jaagro.microservice.platform.crm.entity.ContractSectionPrice;
import com.jaagro.microservice.platform.crm.entity.response.ContractSectionPriceReturnDto;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author baiyiran
 */
public interface ContractSectionPriceMapper {

    /**
     * 删除
     *
     * @param selectionId
     * @return
     */
    int deleteByPrimaryKey(Integer selectionId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(ContractSectionPrice record);

    /**
     * 新增 动态的
     *
     * @param record
     * @return
     */
    int insertSelective(ContractSectionPrice record);

    /**
     * 查询单个
     *
     * @param selectionId
     * @return
     */
    ContractSectionPrice selectByPrimaryKey(Integer selectionId);

    /**
     * 动态的修改
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ContractSectionPrice record);

    /**
     * 根据主键修改
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(ContractSectionPrice record);

    /**
     * 根据外键删除
     *
     * @param id
     * @return
     */
    int deleteByPriceId(Long id);

    /**
     * 根据外键查询
     *
     * @param priceId
     * @return
     */
    List<ContractSectionPrice> getByFKId(Long priceId);

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