package com.codebillionz.android.hng_internship_app.Models;

public class Members {
    String imageUrl;
    String usersName;
    String otherDetails;

    public Members(String imageUrl, String usersName, String otherDetails) {
        this.imageUrl = imageUrl;
        this.usersName = usersName;
        this.otherDetails = otherDetails;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }
}
