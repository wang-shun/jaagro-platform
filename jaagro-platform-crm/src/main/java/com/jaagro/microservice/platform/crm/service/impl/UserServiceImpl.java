package com.jaagro.microservice.platform.crm.service.impl;

import com.jaagro.microservice.platform.api.service.auth.VerifyTokenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tony
 */
@Service
public class UserServiceImpl {

    @Autowired
    private VerifyTokenClient tokenClient;


}
