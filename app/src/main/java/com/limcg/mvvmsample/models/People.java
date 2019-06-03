package com.limcg.mvvmsample.models;

public class People {

    private String profileImgUrl;
    private String profileName;

    public People(String profileImgUrl, String profileName)
    {
        this.profileImgUrl = profileImgUrl;
        this.profileName = profileName;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
