package com.tomkato.fitnesstracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tomkato.fitnesstracker.exercise.Exercise;
import com.tomkato.fitnesstracker.exercise.ExerciseRepository;

@Controller
public class MainController {
    
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    private ExerciseRepository exerciseRepository;
 
    @GetMapping(path="/")
    public String welcomePage(Model model) {
        return "welcome";
    }
    
    @GetMapping(path="/home")
    public String homePage(Model model) {
        return "home";
    }
    
    @GetMapping(path="/log")
    public @ResponseBody List<Exercise> getExerciseLog() {
        
        log.info("Serving GET request");
        List<Exercise> exercises = new ArrayList<>();
        exercises = exerciseRepository.findAll();
        
        //debug
        log.info("SQL query: SELECT * FROM exercises");
        for (Exercise e: exercises) {
            log.info("Exercise: {}", e.getName());
            log.info("Duration: {} minutes", e.getDuration());
        }
        
        return exercises;
    }
    
    @GetMapping(path="/record")
    public String recordPage(Model model) {
        return "record";
    }
    
    @GetMapping(path="/log/{id}")
    public @ResponseBody Exercise getExerciseById(@PathVariable Integer id) {
        log.info("Serving GET request");
        Exercise exercise = exerciseRepository.findExerciseById(id);
        return exercise;
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String logExercise(@RequestParam String name, @RequestParam Integer duration) {
        log.info("Serving POST request");
        
        int rc = 0;
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setDuration(duration);
        
        rc = exerciseRepository.save(exercise);
        log.info("Return code: {}", rc);
        return "POST request complete";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String removeExercise(@PathVariable Integer id) {
        log.info("Serving DELETE request");
        int rc = exerciseRepository.deleteById(id);
        log.info("DELETE request status code: {}", rc);
        return "DELETE request complete";
    }
}