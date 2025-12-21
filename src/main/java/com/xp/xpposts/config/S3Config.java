package com.xp.xpposts.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
//import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class S3Config {

    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .region(Region.US_EAST_2) // 🔴 change if needed
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
//    @Bean
//    public SqsClient sqsClient() {
//        // This forces creation of the bean if autoconfig fails
//        return SqsClient.builder()
//                .region(Region.of("us-east-2"))
//                // Credentials will still be picked up from env vars or application.yml
//                .build();
//    }
}
