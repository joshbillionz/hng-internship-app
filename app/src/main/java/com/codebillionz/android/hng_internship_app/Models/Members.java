package com.codebillionz.android.hng_internship_app.Models;

public class Members {
    String imageUrl;
    String usersName;
    String name;
    String whatYouDo;
    String phoneNumber;
    String email;
    String otherDetails;

    public Members(String imageUrl, String usersName, String name,
                   String whatYouDo, String phoneNumber, String email, String otherDetails) {
        this.imageUrl = imageUrl;
        this.usersName = usersName;
        this.name = name;
        this.whatYouDo = whatYouDo;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.otherDetails = otherDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhatYouDo() {
        return whatYouDo;
    }

    public void setWhatYouDo(String whatYouDo) {
        this.whatYouDo = whatYouDo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
