package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.UserBranch;

public interface UserBranchMapper {
    int deleteByPrimaryKey(Long userBranchId);

    int insert(UserBranch record);

    int insertSelective(UserBranch record);

    UserBranch selectByPrimaryKey(Long userBranchId);

    int updateByPrimaryKeySelective(UserBranch record);

    int updateByPrimaryKey(UserBranch record);
}