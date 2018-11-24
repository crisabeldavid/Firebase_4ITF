package com.bel.firebase;

public class Student {
    //Long id;
    String fname, lname;
    Long grade;

    public Student(String fname, String lname, Long grade) {
        this.fname = fname;
        this.lname = lname;
        this.grade = grade;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Long getGrade() {
        return grade;
    }
}
