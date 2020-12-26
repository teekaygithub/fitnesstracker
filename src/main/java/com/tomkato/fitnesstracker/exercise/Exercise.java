package com.tomkato.fitnesstracker.exercise;

public class Exercise {
    
    private Integer id;
    private String name;
    private Integer duration;
    private String date;
    
    public Exercise () {}
    
    public Exercise (Integer id, String name, Integer duration, String date) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.date = date;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getDuration () {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
}