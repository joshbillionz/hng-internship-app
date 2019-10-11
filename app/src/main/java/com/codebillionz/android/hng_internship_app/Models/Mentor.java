package com.codebillionz.android.hng_internship_app.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Mentor {

    private final String USER_TYPE =  "mentor";
    private String id;
    private String name;
    private String track;
    private String displayName;
    private String email;
    private List students;


    public Mentor(){

    }
    public Mentor(String id, String name,  String displayName, String email, String track){
         this.id= id;
         this.name=name;
         this.displayName = displayName;
         this.track = track;
         this.email= email;
         this.students = new ArrayList<UUID>();

    }

    //GETTERS


    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTrack() {
        return track;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public List getStudents() {
        return students;
    }


    //SETTERS


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudents(List students) {
        this.students = students;
    }
}
