package com.jaagro.microservice.platform.common.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaagro.microservice.platform.common.utils.VerificationPhone;

import java.util.Map;

/**
 * @author tony
 */
public class AliyunSmsUtils {

    /**短信账号信息*/
    private static String accessKeyId = "LTAIFSq71xbZMfJD";
    private static String accessKeySecret = "ghHnteMFpyf8aM4i9BvkeTputjqyNN";
    private static String signName = "健安";

    /** 产品名称:云通信短信API产品,开发者无需替换*/
    private static final String product = "Dysmsapi";
    /** 产品域名,开发者无需替换 */
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static ObjectMapper MAPPER = new ObjectMapper();

    public static SendSmsResponse sendSms(String phoneNumber, String templateCode, Map<String, Object> templateMap) throws ClientException {

        if(!VerificationPhone.isMobile(phoneNumber)){
            throw new RuntimeException("请输入有效的11位中国大陆手机号！");
        }
        //将短信模板变量map转换成json
        String templateParams = null;
        try {
            templateParams = MAPPER.writeValueAsString(templateMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParams);
        //此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
}
