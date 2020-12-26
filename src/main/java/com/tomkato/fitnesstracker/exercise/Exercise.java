package com.tomkato.fitnesstracker.exercise;

public class Exercise {
    
    private Integer id;
    private String name;
    private Integer duration;
    
    public Exercise () {}
    
    public Exercise (Integer id, String name, Integer duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
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
    
}