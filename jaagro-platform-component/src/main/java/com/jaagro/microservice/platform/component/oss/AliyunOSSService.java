package com.jaagro.microservice.platform.component.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;

@Service
public class AliyunOSSService {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucket}")
    private String bucket;
    @Value("${aliyun.oss.basedir}")
    private String baseDir;

    private OSSClient ossClient;

    public AliyunOSSService() {
    }

    @PostConstruct
    void init() {
        if (this.ossClient == null) {
            this.ossClient = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
        }
    }

    /**
     * 获取Policy签名等信息
     *
     * @param dir            存储在bucket的目录
     * @param expiredSeconds 过期时间
     * @return
     */
    public PostObjectPolicy getPostObjectPolicy(String dir, long expiredSeconds) {
        dir = baseDir + "/" + dir + "/" + System.currentTimeMillis();

        long expireEndTime = System.currentTimeMillis() + expiredSeconds * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = new byte[0];
        try {
            binaryData = postPolicy.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        PostObjectPolicyCallback callback = new PostObjectPolicyCallback();

        PostObjectPolicy policy = new PostObjectPolicy();
        policy.setAccessId(accessKeyId);
        policy.setHost("https://" + bucket + "." + endpoint);
        policy.setDir(dir);
        policy.setExpire(String.valueOf(expireEndTime / 1000));
        policy.setPolicy(encodedPolicy);
        policy.setSignature(postSignature);
        return policy;
    }


    public URL keyToUrl(String objectKey) {
        // 设置URL过期时间为1小时
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucket, objectKey, expiration);
        return url;
    }

    private URL getUrl(String bucketName, String objectKey) {
        // 设置URL过期时间为1小时
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, objectKey, expiration);
        return url;
    }

    public void deleteByKey(String key) {
        ossClient.deleteObject(bucket, key);
    }

}
