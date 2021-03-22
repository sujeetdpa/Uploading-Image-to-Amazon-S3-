package com.aspd.awsimageupload.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
	@Bean
    public AmazonS3 s3(){
        BasicAWSCredentials awsCredentials=new BasicAWSCredentials("AKIAWHWCXPF7MGEW7AZW","nXG7OYTgyC8kTxG8DR6vW/mUChWbsIXivEjFp9GX");
        return AmazonS3ClientBuilder.standard()
        		.withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
