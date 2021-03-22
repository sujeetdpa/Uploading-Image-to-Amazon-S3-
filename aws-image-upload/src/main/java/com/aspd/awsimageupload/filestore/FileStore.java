package com.aspd.awsimageupload.filestore;


import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    @Autowired
    private  AmazonS3 s3;
    		

    public void save(String path, String fileName, Optional<Map<String,String>> metadata, InputStream inputStream){
        ObjectMetadata objectMetadata=new ObjectMetadata();
        metadata.ifPresent(map -> {
            if(!map.isEmpty()){
                map.forEach((key,value)->objectMetadata.addUserMetadata(key,value));

            }
        });

        try {
            s3.putObject(path,fileName,inputStream,objectMetadata);
        }catch (AmazonS3Exception e){
            throw new IllegalStateException("Failed to store the file to S3"+e);
        }
    }

    public byte[] download(String path, String filename) {
        try{
            S3Object userProfileImage=s3.getObject(path,filename);
            S3ObjectInputStream inputStream= userProfileImage.getObjectContent();
            return inputStream.readAllBytes();
        }catch (AmazonS3Exception | IOException e){
            throw new IllegalStateException("Failed to download image "+e);
        }
    }
}
