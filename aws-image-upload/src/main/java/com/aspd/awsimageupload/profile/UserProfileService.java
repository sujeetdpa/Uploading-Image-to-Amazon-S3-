package com.aspd.awsimageupload.profile;

import com.aspd.awsimageupload.buckets.BucketName;
import com.aspd.awsimageupload.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {
    @Autowired
    private  UserProfileDataAccessService userProfileDataAccessService;
    @Autowired
    private FileStore fileStore;
    public List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

	public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) throws IOException {
		//1.check  if image is not empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Empty file cannot be uploaded...["+file.getSize()+"]");
        }
		//2.check if file is an image
        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType()
                ,ContentType.IMAGE_PNG.getMimeType()
                ,ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("Invalid Image Format:"+file.getContentType());
        }
		//3.the user exist in database
        UserProfile user=userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(()->new IllegalStateException(String.format("User Profile %s not found"+userProfileId)));
        //4.grab some metadata
        Map<String,String> metadata=new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));
		//5.store the image in s3 bucket and update the image link
        String path=String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),user.getUserProfileId());
        String filename =file.getOriginalFilename()+"-"+UUID.randomUUID();
        fileStore.save(path,filename, Optional.of(metadata),file.getInputStream());
        user.setUserProfileImageLink(filename);
	}

    public byte[] downloadUserProfileImge(UUID userProfileId) {
        UserProfile user=userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(()->new IllegalStateException(String.format("User Profile %s not found"+userProfileId)));
        String path=String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),user.getUserProfileId());
        return user.getUserProfileImageLink()
                .map(key->fileStore.download(path,key))
                .orElse(new byte[0]);


    }
}
