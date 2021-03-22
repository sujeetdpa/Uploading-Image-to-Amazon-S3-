package com.aspd.awsimageupload.profile;

import com.aspd.awsimageupload.datastore.FakeUserProfileDatastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDataAccessService {
    @Autowired
    private   FakeUserProfileDatastore fakeUserProfileDatastore;
    List<UserProfile> getUserProfiles(){
        return fakeUserProfileDatastore.getUserProfiles();
    }
}
