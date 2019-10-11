package com.codebillionz.android.hng_internship_app.Models;

import java.util.UUID;

public class AppUser {
    private String userType ;
    private String id;

    public AppUser(){

    }
    public AppUser(String id,String userType ){
        this.userType=userType;
        this.id=id;

    }

    public String getUserType() {
        return userType;
    }

    public String getId() {
        return id;
    }

    //setters


    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setId(String id) {
        this.id = id;
    }
}
