package kz.baltabayev.storageservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Amazon S3.
 * This class provides a bean that is used to interact with Amazon S3.
 */
@Slf4j
@Configuration
public class S3Config {

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretKey;

    /**
     * Creates a new AmazonS3 instance using the provided AWS credentials and region.
     *
     * @return a new AmazonS3 instance.
     */
    @Bean
    AmazonS3 s3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials
                (
                accessKey, secretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
