package com.codebillionz.android.hng_internship_app.Models;

public class Announcement {
    private String message;
    private String Id;
    private String senderName;


    public Announcement(){

    }

    public Announcement(String id, String message , String senderName){
        this.Id= id;
        this.message = message;
        this.senderName= senderName;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return Id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
