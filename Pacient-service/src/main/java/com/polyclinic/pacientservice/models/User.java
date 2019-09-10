package com.polyclinic.pacientservice.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;


public class User {
    private String id;
public User(){

}
public User(String id){
    this.id = id;
}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
