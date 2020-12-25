package com.tomkato.fitnesstracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {
    
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping(path="/log")
    public @ResponseBody String getExerciseLog() {
        log.info("Serving GET request");
        log.info("Creating table...");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS exercises (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255) NOT NULL, duration INT NOT NULL, PRIMARY KEY(id))");
        
        log.info("SELECT * FROM exercises");
        jdbcTemplate.execute("SELECT * FROM exercises");
        return "GET request complete";
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String logExercise(@RequestParam String name, @RequestParam Integer duration) {
        log.info("Serving POST request");
        int rc = 0;
        rc = jdbcTemplate.update("INSERT INTO exercises VALUES (0, ?, ?)", name, duration);
        log.info("Return code: %d", rc);
        return "POST request complete";
    } 
}