package com.example.demo.config;

import com.aliyun.oss.OSSClient;

/**
 * Created by Administrator on 2017/1/11.
 */
public class AliYunOssConfig {

    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";

    private static String bucketWeifen = "weifen-test";
    private static String bucketTest = "weifen-test";

    private OSSClient ossClient;

    public AliYunOssConfig(){

    }

    public static OSSClient generateNewOssClient() {
//        if (ossClient == null) ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//        return ossClient;
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public static String getBucketWeifen() {
        return bucketWeifen;
    }

    public static String getBucketTest() {
        return bucketTest;
    }
}
