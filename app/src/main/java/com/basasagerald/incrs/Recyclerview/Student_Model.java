package com.basasagerald.incrs.Recyclerview;

/**
 * Created by basasagerald on 3/1/2017.
 */

public class Student_Model {
    private String title;
    private String question;

    public Student_Model() {

    }

    public Student_Model(String title, String question) {
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
