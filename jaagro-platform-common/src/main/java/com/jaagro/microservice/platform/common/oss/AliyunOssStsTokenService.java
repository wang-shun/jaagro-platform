package com.jaagro.microservice.platform.common.oss;

import java.util.Map;

public interface AliyunOssStsTokenService {

    Map<String, Object> generateStsToken();
}
