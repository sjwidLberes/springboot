package com.example.demo.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.example.demo.config.AliYunOssConfig;
import com.example.demo.util.common.DownloadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/12.
 */
@Service
public class AliYunOssService {
    Logger logger = LoggerFactory.getLogger(AliYunOssService.class);


    public boolean test() {
        OSSClient client = AliYunOssConfig.generateNewOssClient();
        try {
            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (client.doesBucketExist(AliYunOssConfig.getBucketTest())) {
                logger.info("您已经创建Bucket：" + AliYunOssConfig.getBucketTest() + "。");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + AliYunOssConfig.getBucketTest() + "。");
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                client.createBucket(AliYunOssConfig.getBucketTest());
            }
            // 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            BucketInfo info = client.getBucketInfo(AliYunOssConfig.getBucketTest());
            logger.info("Bucket " + AliYunOssConfig.getBucketTest() + "的信息如下：");
            logger.info("\t数据中心：" + info.getBucket().getLocation());
            logger.info("\t创建时间：" + info.getBucket().getCreationDate());
            logger.info("\t用户标志：" + info.getBucket().getOwner());

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        } finally {
            client.shutdown();
        }

        return true;
    }

    //根据url，自动下载，然后上传
    public String addObjectToOssByUrl(String bucketName, String reference, String userId, String objectOriginUrl){
        OSSClient ossClient = AliYunOssConfig.generateNewOssClient();
        String objectPath = String.format("%s/%s", reference, userId);

        try {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, objectPath);
            // 待上传的本地文件
            uploadFileRequest.setUploadFile(DownloadUtil.download(objectOriginUrl).getAbsolutePath());
            // 设置并发下载数，默认1
            uploadFileRequest.setTaskNum(5);
            // 设置分片大小，默认100KB
            uploadFileRequest.setPartSize(1024 * 1024 * 1);
            // 开启断点续传，默认关闭
            uploadFileRequest.setEnableCheckpoint(true);

            UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);

            CompleteMultipartUploadResult multipartUploadResult = uploadResult.getMultipartUploadResult();
            logger.info(multipartUploadResult.getETag());
        } catch (OSSException oe) {
            logger.error("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
            logger.error("Error Message: " + oe.getErrorCode());
            logger.error("Error Code:       " + oe.getErrorCode());
            logger.error("Request ID:      " + oe.getRequestId());
            logger.error("Host ID:           " + oe.getHostId());
            return null;
        } catch (ClientException ce) {
            logger.error("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
            logger.error("Error Message: " + ce.getMessage());
            return null;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            ossClient.shutdown();
        }

        String objectOpenUrl = getObjectUrl(bucketName, objectPath);
        return objectOpenUrl;
    }

    //用户手动上传
    public String addObjectToOssByMultipartFile(String bucketName, String reference, String userId, MultipartFile file) throws RuntimeException {
        logger.info("addObjectToOssByMultipartFile.......");
        logger.info("bucketName: " + bucketName);

        OSSClient client = AliYunOssConfig.generateNewOssClient();
        String objectPath = null;

//        objectPath = String.format("%s/%s%s", reference, userId, file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        objectPath = String.format("%s/%s", reference, userId);

        try {
            PutObjectResult result = client.putObject(bucketName, objectPath, file.getInputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage());
            return null;
        } finally {
            client.shutdown();
        }

        return getObjectUrl(bucketName, objectPath);
    }

    //获取图片外网地址
    private String getObjectUrl(String bucketName, String objectPath) {
        logger.info("getObjectUrl.......");
        logger.info("bucketName: " + bucketName + " objectPath: %s" + objectPath);
        OSSClient client = AliYunOssConfig.generateNewOssClient();

        URL url = null;
        try {
            // 设置URL过期时间为1年  3600l* 1000*24*365
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 99);
            url = client.generatePresignedUrl(bucketName, objectPath, expiration);
        } catch (ClientException e) {
            logger.error(e.getMessage());
        } finally {
            client.shutdown();
        }

        if (url != null)
            return url.toString();
        else
            return null;
    }

    public String delete() {

        return null;
    }


    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FileExtension 文件后缀
     * @return String
     */
    private static String getObjectType(String FileExtension) {
        if (FileExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (FileExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (FileExtension.equalsIgnoreCase("jpeg") ||
                FileExtension.equalsIgnoreCase("jpg") ||
                FileExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (FileExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (FileExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (FileExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (FileExtension.equalsIgnoreCase("pptx") ||
                FileExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FileExtension.equalsIgnoreCase("docx") ||
                FileExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (FileExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

}
