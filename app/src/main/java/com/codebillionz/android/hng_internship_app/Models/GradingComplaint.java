package com.codebillionz.android.hng_internship_app.Models;



import java.util.Date;
import java.util.UUID;

public class GradingComplaint {
    private UUID id;
    private Date dateLaid;
    private Date dateSolved;
    private Intern complainant;
    private Mentor problemSolver;
    private String title;
    private String description;
    private int stage;
    private boolean isSolved;

    GradingComplaint(){

    }
    GradingComplaint(Date dateLaid, Intern complainant, String title, String description, int stage ){
      this.dateLaid= dateLaid;
      this.complainant = complainant;
      this.title = title;
      this.description = description;
      this.stage= stage;
    }

    //Getters


    public UUID getId() {
        return id;
    }

    public Date getDateLaid() {
        return dateLaid;
    }

    public Date getDateSolved() {
        return dateSolved;
    }

    public Intern getComplainant() {
        return complainant;
    }

    public Mentor getProblemSolver() {
        return problemSolver;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getStage() {
        return stage;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setDateLaid(Date dateLaid) {
        this.dateLaid = dateLaid;
    }

    public void setDateSolved(Date dateSolved) {
        this.dateSolved = dateSolved;
    }

    public void setComplainant(Intern complainant) {
        this.complainant = complainant;
    }

    public void setProblemSolver(Mentor problemSolver) {
        this.problemSolver = problemSolver;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
//setters

}
