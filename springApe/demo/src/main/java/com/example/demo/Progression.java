package com.example.demo;

import java.util.ArrayList;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "progression") // if collection doesn't exist, it will be created
public class Progression {

    @Id
    private String username;

    private int liftId;
    private ArrayList<Float> progressionPlan;

    public Progression(String username, int liftId, ArrayList<Float> progressionPlan) {
        super();
        this.username = username;
        this.liftId = liftId;
        this.progressionPlan = progressionPlan;
    }

    public String getUsername() {
        return username;
    }

    public int getLiftId() {
        return liftId;
    }

    public ArrayList<Float> getProgressionPlan() {
        return progressionPlan;
    }

}
