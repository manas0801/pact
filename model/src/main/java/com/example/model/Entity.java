package com.example.model;

public class Entity {





    private String name;



    private String timestamp;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Entity(String name, String timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    public Entity(String name) {
        this.name = name;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    public Entity() {

    }
}
