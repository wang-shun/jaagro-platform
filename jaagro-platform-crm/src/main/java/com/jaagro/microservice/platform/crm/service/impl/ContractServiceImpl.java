package com.jaagro.microservice.platform.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaagro.microservice.platform.api.dto.crm.*;
import com.jaagro.microservice.platform.api.dto.crm.request.ContractCriteriaDto;
import com.jaagro.microservice.platform.api.dto.crm.response.ContractReturnDto;
import com.jaagro.microservice.platform.api.service.crm.ContractService;
import com.jaagro.microservice.platform.component.utils.ServiceResult;
import com.jaagro.microservice.platform.crm.entity.Contract;
import com.jaagro.microservice.platform.crm.entity.ContractPrice;
import com.jaagro.microservice.platform.crm.entity.ContractSectionPrice;
import com.jaagro.microservice.platform.crm.mapper.ContractLogMapper;
import com.jaagro.microservice.platform.crm.mapper.ContractMapper;
import com.jaagro.microservice.platform.crm.mapper.ContractPriceMapper;
import com.jaagro.microservice.platform.crm.mapper.ContractSectionPriceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private ContractPriceMapper contractPriceMapper;
    @Autowired
    private ContractSectionPriceMapper contractSectionPriceMapper;
    @Autowired
    private ContractLogMapper contractLogMapper;

    /**
     * 创建合同
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> createContract(CreateContractDto dto) {

        //创建contract对象
        Contract contract = new Contract();
        BeanUtils.copyProperties(dto, contract);
        contract
                .setCreateTime(new Date())
                // 先写一个固定值，等userService创建后补充
                .setCreateUser(1L);
        contractMapper.insert(contract);

        //创建contractPrice对象
        if (dto.getPrice().size() > 0) {
            for (ContractPriceDto cp : dto.getPrice()) {
                ContractPrice contractPrice = new ContractPrice();
                BeanUtils.copyProperties(cp, contractPrice);
                contractPrice.setId(contract.getId());
                if (StringUtils.isEmpty(contractPrice.getPricingType())) {
                    throw new RuntimeException("计价方式不能为空");
                }
                contractPriceMapper.insert(contractPrice);
                //创建contractSectionPrice对象
                if (cp.getSectionPrice().size() > 0) {
                    for (ContractSectionPriceDto cspDto : cp.getSectionPrice()) {
                        ContractSectionPrice csp = new ContractSectionPrice();
                        BeanUtils.copyProperties(cspDto, csp);
                        csp.setContractPriceId(contractPrice.getId());
                        contractSectionPriceMapper.insert(csp);
                    }
                }
            }
        }

        return ServiceResult.toResult("合同创建成功");
    }

    /**
     * 修改合同
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> updateContract(CreateContractDto dto) {
        // 创建contract对象
        Contract contract = new Contract();
        BeanUtils.copyProperties(dto, contract);
        contract
                .setNewUpdateTime(new Date())
                // 先写一个固定值，等userService创建后补充
                .setNewUpdateUser(1L);
        contractMapper.updateByPrimaryKeySelective(contract);

        //删除原数据
        List<ContractPrice> priceList = contractPriceMapper.getByContractId(dto.getId());
        if (priceList.size() > 0) {
            for (ContractPrice cp : priceList) {
                contractSectionPriceMapper.deleteByPriceId(cp.getId());
            }
            contractPriceMapper.deleteByContractId(dto.getId());
        }
        //创建contractPrice对象
        if (dto.getPrice().size() > 0) {
            for (ContractPriceDto cp : dto.getPrice()) {
                ContractPrice contractPrice = new ContractPrice();
                BeanUtils.copyProperties(cp, contractPrice);
                contractPrice.setId(contract.getId());
                if (StringUtils.isEmpty(contractPrice.getPricingType())) {
                    throw new RuntimeException("计价方式不能为空");
                }
                contractPriceMapper.insert(contractPrice);
                //创建contractSectionPrice对象
                if (cp.getSectionPrice().size() > 0) {
                    for (ContractSectionPriceDto cspDto : cp.getSectionPrice()) {
                        ContractSectionPrice csp = new ContractSectionPrice();
                        BeanUtils.copyProperties(cspDto, csp);
                        csp.setContractPriceId(contractPrice.getId());
                        contractSectionPriceMapper.insert(csp);
                    }
                }
            }
        }
        return ServiceResult.toResult("合同修改成功");
    }

    /**
     * 查询单个合同
     *
     * @param contractId
     * @return
     */
    @Override
    public Map<String, Object> getContractByPk(Long contractId) {
        Contract contract = contractMapper.selectByPrimaryKey(contractId);
        ContractReturnDto dto = new ContractReturnDto();
        BeanUtils.copyProperties(contract, dto);
        return ServiceResult.toResult(dto);
    }

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> listByPage(ContractCriteriaDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Contract> contracts = contractMapper.listByPage(dto);
        return ServiceResult.toResult(new PageInfo<>(contracts));
    }


}
