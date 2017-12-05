package com.example.demo.main.module;

import java.io.Serializable;

/**
 * Created by ChenTeacher on 2017/12/1.
 */

public class Patient_Case_Collection implements Serializable {
    private  String number;
    private  String time;
    private  String doctor;
    private  String content;

    public Patient_Case_Collection(){}

    public Patient_Case_Collection(String number,String time,String doctor,String content){
        this.content = content;
        this.doctor = doctor;
        this.number = number;
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
