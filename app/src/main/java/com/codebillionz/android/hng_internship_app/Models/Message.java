package com.codebillionz.android.hng_internship_app.Models;

public class Message {
    private String User1;
    private String User2;
    private int PhotoUrl = NO_IMAGE_RESOURCE;

    public static final int NO_IMAGE_RESOURCE = -1;

    public Message(String User1, String User2, int PhotoUrl) {
        this.User1 = User1;
        this.User2 = User1;
        this.PhotoUrl = PhotoUrl;
    }

    public Message(String User1, String User2){
        this.User1 = User1;
        this.User2 = User2;
    }

    public Message(String User1, int PhotoUrl) {
        this.User1 = User1;
        this.PhotoUrl = PhotoUrl;
    }

    public Message() {
    }

    public String getUser1() {
        return User1;
    }

    public void setUser1(String User1) {
        this.User1 = User1;
    }

    public String getUser2() {
        return User2;
    }

    public void setUser2(String User2) {
        this.User2 = User2;
    }

    public int getPhotoUrl() {
        return PhotoUrl;
    }

    public boolean hasImage() {
        return PhotoUrl != NO_IMAGE_RESOURCE;
    }
}
