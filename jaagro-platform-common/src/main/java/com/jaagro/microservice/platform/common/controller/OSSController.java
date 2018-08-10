package com.jaagro.microservice.platform.common.controller;

import com.jaagro.microservice.platform.common.oss.*;
import com.jaagro.microservice.platform.common.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "oss")
public class OSSController {

    @Autowired
    private AliyunOSSService aliyunOSSService;

    @Autowired
    AliyunOssStsTokenService aliyunOssStsTokenService;

    @PostMapping("/getAttachmentPolicy")
    public BaseResponse getAttachmentPolicy(@RequestBody KeyToUrlDto keyToUrl) {
        if (keyToUrl.getSize() == null) {
            throw new RuntimeException(String.format("请附带参数：size"));
        }
        keyToUrl.setPackageName(CurrentDate.getPackageName());
        List<PostObjectPolicy> policies = new ArrayList<>();
        for (int i = 0; i < keyToUrl.getSize(); i++) {
            PostObjectPolicy policy = aliyunOSSService.getPostObjectPolicy(keyToUrl.getPackageName() + "/" + (i+1),300);
            policies.add(policy);
        }
        return BaseResponse.successInstance(policies);
    }

    @PostMapping("/getUrlByKey")
    public BaseResponse getUrlByKey(@RequestBody KeyToUrlDto key) {
        List<URL> urls = new ArrayList<>();
        for (String k : key.getKey()) {
            urls.add(aliyunOSSService.keyToUrl(k));
        }
        return BaseResponse.successInstance(urls);
    }

    @GetMapping("/getUrl")
    public BaseResponse getUrl(@RequestParam String key) {
        return BaseResponse.successInstance(aliyunOSSService.keyToUrl(key));
    }

    @PostMapping("/getStsToken")
    public BaseResponse getSTSToken() {
        Map<String, Object> stsToken = aliyunOssStsTokenService.generateStsToken();
        BaseResponse response = new BaseResponse();
        if (stsToken != null) {
            response.setData(stsToken);
        } else {
            return BaseResponse.errorInstance("服务器无法访问！");
        }
        return response;
    }


}
