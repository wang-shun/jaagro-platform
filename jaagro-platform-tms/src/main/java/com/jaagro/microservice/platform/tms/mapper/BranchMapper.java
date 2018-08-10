package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.Branch;

public interface BranchMapper {

    int deleteByPrimaryKey(Long branchId);

    int insert(Branch record);

    int insertSelective(Branch record);

    Branch selectByPrimaryKey(Long branchId);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);

    Branch getBranchByUser(Long userId);
}