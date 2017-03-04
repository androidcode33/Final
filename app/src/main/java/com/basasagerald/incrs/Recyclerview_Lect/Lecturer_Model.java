package com.basasagerald.incrs.Recyclerview_Lect;

/**
 * Created by basasagerald on 3/2/2017.
 */

public class Lecturer_Model {

    private String title;
    private String question;

    public Lecturer_Model() {

    }

    public Lecturer_Model(String title, String question) {
        this.title = title;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
