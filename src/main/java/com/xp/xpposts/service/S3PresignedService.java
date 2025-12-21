package com.xp.xpposts.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Service
public class S3PresignedService {

    private final S3Presigner presigner;

    public S3PresignedService(S3Presigner presigner) {
        this.presigner = presigner;
    }

//    public URL generatePresignedUrl(String bucket, String key, int expirySeconds) {
//
//        PutObjectRequest objectRequest = PutObjectRequest.builder()
//                .bucket(bucket)
//                .key(key)
//                .contentType("image/jpeg")
//                .build();
//
//        PutObjectPresignRequest presignRequest =
//                PutObjectPresignRequest.builder()
//                        .signatureDuration(Duration.ofSeconds(expirySeconds))
//                        .putObjectRequest(objectRequest)
//                        .build();
//
//        return presigner.presignPutObject(presignRequest).url();
//    }

    public URL generatePresignedUrl(String bucket, String key, int expirySeconds) {

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build(); // ❗ NO contentType

        PutObjectPresignRequest presignRequest =
                PutObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofSeconds(expirySeconds))
                        .putObjectRequest(objectRequest)
                        .build();
        URL url = presigner.presignPutObject(presignRequest).url();
        System.out.println("PRESIGNED URL = " + url);
        return url;

    }

}
