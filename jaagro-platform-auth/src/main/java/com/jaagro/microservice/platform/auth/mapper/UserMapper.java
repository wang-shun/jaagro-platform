package com.jaagro.microservice.platform.auth.mapper;

import com.jaagro.microservice.platform.auth.entity.User;

/**
 * @author tony
 */
public interface UserMapper {

    /**
     * 删除单条
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 动态sql新增
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 通过id查询单条user
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 动态sql更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新全部字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 通过用户名获取user
     * @param username
     * @return
     */
    User getUerByUsername(String username);

    /**
     *
     * @param PhoneNumber
     * @return
     */
    User getUserByPhone(String PhoneNumber);
}