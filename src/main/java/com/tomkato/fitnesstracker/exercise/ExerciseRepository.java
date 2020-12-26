package com.tomkato.fitnesstracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@Repository
public class ExerciseRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public int save (Exercise exercise) {
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        
        return jdbcTemplate.update(
                "insert into exercises (name, duration, date) values (?, ?, ?)",
                exercise.getName(), exercise.getDuration(), today);
    }
    
    public List<Exercise> findAll() {
        String sql = "SELECT * from exercises";
        
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                    new Exercise (
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("duration"),
                            rs.getString("date")
                    )
        );
    }
    
    public Exercise findExerciseById(Integer id) {
        
        Exercise exercise;
        
        try {
            String sql = "SELECT * FROM exercises where ID=?";
            
            exercise = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ExerciseRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return exercise;
    }
    
    public int deleteById(Integer id) {
        int rc=0; // 0 = fail, 1 = success
        Exercise exercise = findExerciseById(id);
        if (exercise != null) {
            String sql = "DELETE FROM exercises WHERE id=?";
            rc=jdbcTemplate.update(sql, id);
        }
        return rc;
    }
}