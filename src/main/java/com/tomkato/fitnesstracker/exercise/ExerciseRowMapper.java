package com.tomkato.fitnesstracker.exercise;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseRowMapper implements RowMapper<Exercise> {
    
    @Override
    public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
        Exercise exercise = new Exercise();
        exercise.setId(rs.getInt("id"));
        exercise.setName(rs.getString("name"));
        exercise.setDuration(rs.getInt("duration"));
        
        return exercise;
    }
}