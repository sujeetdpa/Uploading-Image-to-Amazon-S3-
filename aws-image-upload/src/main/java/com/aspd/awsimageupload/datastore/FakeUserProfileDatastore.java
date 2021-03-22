package com.aspd.awsimageupload.datastore;

import com.aspd.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDatastore {
    private final static List<UserProfile> userProfiles =new ArrayList<>();
    static {
        userProfiles.add(new UserProfile(UUID.fromString("ed76889d-8e1f-404c-92d6-a6e455ed0b15"),"Janet Jones",null));
        userProfiles.add(new UserProfile(UUID.fromString("192706c1-adaf-4555-990c-c3a93ed5cac5"),"Antonio Junior",null));

    }
    public  List<UserProfile> getUserProfiles(){
        return userProfiles;
    }
}
