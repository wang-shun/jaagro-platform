package com.jaagro.microservice.platform.component.oss;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;


@Service("aliyunOssStsTokenService")
public class AliyunOssStsTokenServiceImpl implements AliyunOssStsTokenService {


    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    public static final String STS_API_VERSION = "2015-04-01";

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.roleArn}")
    private String roleArn;

    @Value("${aliyun.oss.tokenExpireTime}")
    private String tokenExpireTime;

    @Value("${aliyun.oss.policyFile}")
    private String policyFile;


    private AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
                                          String roleSessionName, String policy, ProtocolType protocolType, long durationSeconds) throws
            ClientException {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);

            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(durationSeconds);

            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);

            return response;
        } catch (ClientException e) {
            throw e;
        }
    }

    private String ReadJson(File file) {
        //从给定位置获取文件
        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }


    @Override
    public Map<String, Object> generateStsToken() {
        // 只有 RAM用户（子账号）才能调用 AssumeRole 接口
        // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
        // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
        String policy = "";
        try {
            // RoleArn 需要在 RAM 控制台上获取
            ClassPathResource resource = new ClassPathResource("bucket_no_delete_policy.json");
            InputStream inputStream = resource.getInputStream();
            File f = new File("bucket_no_delete_policy.json");
            inputStreamToFile(inputStream, f);
            policy = ReadJson(f);
        } catch (FileNotFoundException e) {
            Map<String, Object> respMap = Maps.newLinkedHashMap();
            respMap.put("StatusCode", "500");
            respMap.put("ErrorCode", "501");
            respMap.put("ErrorMessage", "找不到正确的Policy文件.");
            return respMap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
        // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
        // 具体规则请参考API文档中的格式要求
        String roleSessionName = "alice-001";

        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;

        try {
            final AssumeRoleResponse stsResponse = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName,
                    policy, protocolType, Long.parseLong(tokenExpireTime));

            Map<String, Object> respMap = Maps.newLinkedHashMap();
            respMap.put("StatusCode", "200");
            respMap.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
            respMap.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
            respMap.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
            respMap.put("Expiration", stsResponse.getCredentials().getExpiration());

            return respMap;

        } catch (ClientException e) {
            Map<String, Object> respMap = Maps.newLinkedHashMap();
            respMap.put("StatusCode", "500");
            respMap.put("ErrorCode", e.getErrCode());
            respMap.put("ErrorMessage", e.getErrMsg());
            return respMap;
        }
    }

    public void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
