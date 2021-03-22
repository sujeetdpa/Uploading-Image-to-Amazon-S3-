package com.aspd.awsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import  org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
public class UserProfileController {
    @Autowired
    private  UserProfileService userProfileService;
    
    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }
    
    @PostMapping(path = "/{userProfileId}/image/upload",
    		consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE
    		)
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,@RequestParam("file") MultipartFile file) throws IOException {
     	userProfileService.uploadUserProfileImage(userProfileId,file);
    }
    @GetMapping("/{userProfileId}/image/download")
    public byte[] downloadUserProfileImge(@PathVariable("userProfileId") UUID userProfileId){
        return userProfileService.downloadUserProfileImge(userProfileId);

    }
}
