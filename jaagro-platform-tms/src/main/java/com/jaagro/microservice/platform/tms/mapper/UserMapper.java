package com.jaagro.microservice.platform.tms.mapper;

import com.jaagro.microservice.platform.tms.entity.User;
import feign.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);
}