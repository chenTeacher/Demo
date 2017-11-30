package com.example.demo.main.module;

/**
 * Created by ChenTeacher on 2017/11/28.
 */

public class Person {

    private String name;
    private int id;

    public Person(String name,int id){
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[name : "+this.name+",id : "+this.id;
    }
}
