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

    // Inject the credentials and region from application.yml
    @Value("${aws.s3.region}")
    private String awsRegion;

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretAccessKey}")
    private String secretKey;

    @Bean
    public S3Client s3Client() {
        // Manually create the credentials object
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Explicitly provide the credentials and region to the S3 Client builder
        return S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(credentials)) // <--- Add this line
                .build();
    }

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
