package com.tomkato.fitnesstracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExerciseRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public int save (Exercise exercise) {
        return jdbcTemplate.update(
                "insert into exercises (name, duration) values (?, ?)",
                exercise.getName(), exercise.getDuration());
    }
    
    public List<Exercise> findAll() {
        String sql = "SELECT * from exercises";
        
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                    new Exercise (
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("duration")
                    )
        );
    }
}