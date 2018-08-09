package com.jaagro.microservice.platform.component.oss;

import java.util.Map;

public interface AliyunOssStsTokenService {

    Map<String, Object> generateStsToken();
}
