package com.codebillionz.android.hng_internship_app.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Intern {
    private final String USER_TYPE =  "intern";
    private String id;
    private String name;
    private String track;
    private String displayName;
    private String email;
    private int stage;
    private List mentors;
    private List complaints;
    private List inbox;
    private boolean isUnderProbation;
    private boolean isEvicted;

    public Intern(){

    }

    public Intern(String id, String name, String displayName, String email, String track, int stage){
        this.id= id;
        this.name= name;
        this.displayName= displayName;
        this.email= email;
        this.track=track;
        this.stage= stage;
        this.mentors= new ArrayList<UUID>();
        this.complaints =new ArrayList<UUID>();
        this.inbox = new ArrayList<UUID>();
        this.isUnderProbation = false;
        this.isEvicted= false;
    }

    //Getters


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

    public int getStage() {
        return stage;
    }

    public List getMentors() {
        return mentors;
    }

    public List getComplaints() {
        return complaints;
    }

    public List getInbox() {
        return inbox;
    }

    public boolean isUnderProbation() {
        return isUnderProbation;
    }

    public boolean isEvicted() {
        return isEvicted;
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

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setMentors(List mentors) {
        this.mentors = mentors;
    }

    public void setComplaints(List complaints) {
        this.complaints = complaints;
    }

    public void setInbox(List inbox) {
        this.inbox = inbox;
    }

    public void setUnderProbation(boolean underProbation) {
        isUnderProbation = underProbation;
    }

    public void setEvicted(boolean evicted) {
        isEvicted = evicted;
    }

    //todo
    //ADDERS - for lists
    //REMOVERS - for lists
}
