package com.codebillionz.android.hng_internship_app.Models;

public class Message {

    private String senderId;
    private String message;


    public Message(){

    }
    public Message(String senderId, String message ){

    }

    //getters
    public String getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }


    //setters


    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
