package com.jaagro.microservice.platform.tms.service;

import com.jaagro.microservice.platform.api.dto.auth.TokenInfo;
import com.jaagro.microservice.platform.api.service.auth.VerifyTokenClient;
import com.jaagro.microservice.platform.tms.entity.Branch;
import com.jaagro.microservice.platform.tms.entity.User;
import com.jaagro.microservice.platform.tms.mapper.BranchMapper;
import com.jaagro.microservice.platform.tms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于通过老系统token获取user信息
 * @author tony
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private VerifyTokenClient tokenClient;

    public User getCurrentUser(){
        TokenInfo info = getCurrentTokenInfo();
        String username = info.getUsername();
        User user = userMapper.selectByUsername(username);
        return user;
    }

    public Branch getCurrentBranch(){
        Long userId = getCurrentUser().getUserId();
        Branch userBranch = branchMapper.getBranchByUser(userId);
        return userBranch;
    }

    public TokenInfo getCurrentTokenInfo(){
        String token = request.getHeader("token");
        return tokenClient.getTokenInfo(token);
    }
}
