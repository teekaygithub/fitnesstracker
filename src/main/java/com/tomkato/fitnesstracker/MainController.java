package com.tomkato.fitnesstracker; // TODO: create separate controller package

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

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
    
    @PostMapping(path="/add")
    public @ResponseBody String logExercise(@RequestParam String name, @RequestParam Integer duration) {
        log.info("Serving POST request");
        // int rc = 0;
        // rc = jdbcTemplate.update("INSERT INTO exercises VALUES (0, ?, ?)", name, duration);
        // log.info("Return code: %d", rc);
        return "POST request complete";
    } 
}